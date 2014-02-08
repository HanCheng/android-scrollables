package com.dmccartney.android.scrollables;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;

import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Math.abs;

/*
 * This extends the default android.widget.ListView to support multiple OnScrollListeners and to
 * support tracking the vertical scroll changes using OnScrollDeltaYListeners.
 */
public class ListView extends android.widget.ListView {

    public static interface OnScrollDeltaYListener {
        void onScrollDeltaY(int deltaY);
    }

    private DispatchingOnScrollListener dispatch = null;
    private DeltaYDispatchingOnScrollListener deltaYDispatch = null;

    public ListView(Context context) {
        super(context);
    }

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addOnScrollDeltaYListenerSmoothed(OnScrollDeltaYListener listener) {
        addOnScrollDeltaYListener(SmoothingOnScrollDeltaYListener.wrappingListener(listener));
    }

    public void addOnScrollDeltaYListener(OnScrollDeltaYListener listener) {
        if(deltaYDispatch == null) {
            deltaYDispatch = new DeltaYDispatchingOnScrollListener(this);
            addOnScrollListener(deltaYDispatch);
        }
        deltaYDispatch.addListener(listener);
    }

    public void addOnScrollListener(OnScrollListener listener) {
        if(dispatch == null) {
            dispatch = new DispatchingOnScrollListener();
            super.setOnScrollListener(dispatch);
        }
        dispatch.addListener(listener);
    }

    // identical to #addOnScrollListener (overridden to protect implementation)
    @Override public void setOnScrollListener(OnScrollListener listener) {
        addOnScrollListener(listener);
    }

    private class DeltaYDispatchingOnScrollListener  implements OnScrollListener {

        private final ListViewVerticalScrollTracker tracker;
        private final CopyOnWriteArrayList<OnScrollDeltaYListener> listeners;

        private DeltaYDispatchingOnScrollListener(ListView list) {
            this.tracker = new ListViewVerticalScrollTracker(list);
            this.listeners = new CopyOnWriteArrayList<OnScrollDeltaYListener>();
        }

        @Override public void onScrollStateChanged(AbsListView view, int scrollState) {}

        @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            int deltaY = tracker.calculateDeltaYFromScrollPositions(firstVisibleItem, visibleItemCount);
            for (OnScrollDeltaYListener listener : listeners) {
                listener.onScrollDeltaY(deltaY);
            }
        }

        public void addListener(OnScrollDeltaYListener listener) {
            listeners.addIfAbsent(listener);
        }
    }
    private class DispatchingOnScrollListener implements OnScrollListener {

        private final CopyOnWriteArrayList<OnScrollListener> listeners = new CopyOnWriteArrayList<OnScrollListener>();

        @Override public void onScrollStateChanged(AbsListView view, int scrollState) {
            for (OnScrollListener listener : listeners) {
                listener.onScrollStateChanged(view, scrollState);
            }
        }

        @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            for (OnScrollListener listener : listeners) {
                listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        }

        public void addListener(OnScrollListener listener) {
            listeners.addIfAbsent(listener);
        }
    }

    private static class SmoothingOnScrollDeltaYListener implements OnScrollDeltaYListener {

        public static final int DEFAULT_SMOOTHING_THRESHOLD = 25;

        private final OnScrollDeltaYListener wrappedListener;
        private final int smoothingThreshold;

        private int accumulatedDeltaY;

        public SmoothingOnScrollDeltaYListener(OnScrollDeltaYListener wrappedListener, int smoothingThreshold) {
            this.wrappedListener = wrappedListener;
            this.smoothingThreshold = smoothingThreshold;
            this.accumulatedDeltaY = 0;
        }

        @Override public void onScrollDeltaY(int deltaY) {
            accumulatedDeltaY = accumulatedDeltaY + deltaY;
            if(abs(accumulatedDeltaY) > smoothingThreshold) {
                wrappedListener.onScrollDeltaY(accumulatedDeltaY);
                accumulatedDeltaY = 0;
            }
        }

        public static OnScrollDeltaYListener wrappingListener(OnScrollDeltaYListener listener) {
            return wrappingListenerToThreshold(listener, DEFAULT_SMOOTHING_THRESHOLD);
        }

        public static OnScrollDeltaYListener wrappingListenerToThreshold(OnScrollDeltaYListener listener, int smoothingThreshold) {
            return new SmoothingOnScrollDeltaYListener(listener, smoothingThreshold);
        }
    }
}

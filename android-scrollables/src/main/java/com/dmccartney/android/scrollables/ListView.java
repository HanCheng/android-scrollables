package com.dmccartney.android.scrollables;


import android.content.Context;
import android.util.AttributeSet;

/*
 * This extends the default android.widget.ListView to support multiple OnScrollListeners and to
 * support tracking the vertical scroll changes using OnScrollDeltaYListeners.
 */
public class ListView extends android.widget.ListView implements ListViewVerticalScrollTracker.Trackable {

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

    @Override public int getChildOffset(int position) {
        return getChildAt(position).getTop();
    }

}

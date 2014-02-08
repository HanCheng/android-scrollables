package com.dmccartney.android.scrollables;

import android.content.Context;
import android.util.AttributeSet;

import java.util.concurrent.CopyOnWriteArrayList;

/*
 * This extends the default android.widget.ScrollView to support OnScrollChangedListeners.
 */
public class ScrollView extends android.widget.ScrollView {

    private final CopyOnWriteArrayList<OnScrollChangedListener> listeners = new CopyOnWriteArrayList<OnScrollChangedListener>();

    public ScrollView(Context context) {
        super(context);
    }

    public ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        for (OnScrollChangedListener listener : listeners) {
            listener.onScrollChanged(this, x, y, oldX, oldY);
        }
    }

    public void addOnScrollChangedListener(OnScrollChangedListener listener) {
        listeners.addIfAbsent(listener);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(ScrollView view, int x, int y, int oldX, int oldY);
    }
}


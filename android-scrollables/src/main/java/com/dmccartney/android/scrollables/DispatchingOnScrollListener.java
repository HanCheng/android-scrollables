package com.dmccartney.android.scrollables;

import android.widget.AbsListView;

import java.util.concurrent.CopyOnWriteArrayList;


class DispatchingOnScrollListener implements AbsListView.OnScrollListener {

    private final CopyOnWriteArrayList<AbsListView.OnScrollListener> listeners = new CopyOnWriteArrayList<AbsListView.OnScrollListener>();

    @Override public void onScrollStateChanged(AbsListView view, int scrollState) {
        for (AbsListView.OnScrollListener listener : listeners) {
            listener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        for (AbsListView.OnScrollListener listener : listeners) {
            listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public void addListener(AbsListView.OnScrollListener listener) {
        listeners.addIfAbsent(listener);
    }
}

package com.dmccartney.android.scrollables;

import android.widget.AbsListView;

import java.util.concurrent.CopyOnWriteArrayList;


class DeltaYDispatchingOnScrollListener implements AbsListView.OnScrollListener {

    private final ListViewVerticalScrollTracker tracker;
    private final CopyOnWriteArrayList<ListView.OnScrollDeltaYListener> listeners;

    DeltaYDispatchingOnScrollListener(ListView list) {
        this.tracker = new ListViewVerticalScrollTracker(list);
        this.listeners = new CopyOnWriteArrayList<ListView.OnScrollDeltaYListener>();
    }

    @Override public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int deltaY = tracker.calculateDeltaYFromScrollPositions(firstVisibleItem, visibleItemCount);
        for (ListView.OnScrollDeltaYListener listener : listeners) {
            listener.onScrollDeltaY(deltaY);
        }
    }

    public void addListener(ListView.OnScrollDeltaYListener listener) {
        listeners.addIfAbsent(listener);
    }
}

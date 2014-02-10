package com.dmccartney.android.scrollables;


import android.util.SparseArray;

/*
 * This tracks change in the vertical scroll position of a trackable ListView.
 * It gives you the "deltaY" of your scroll position by tracking the ongoing top-position changes of
 * visible children.
 *
 * Usage: call #calculateDeltaYFromScrollPositions in your OnScrollListener to both track the new
 *        scroll position, and to receive the change in vertical scroll position (deltaY).
 */
public class ListViewVerticalScrollTracker {
    private final Trackable list;
    private SparseArray<Integer> positions;

    public static interface Trackable {
        int getChildOffset(int position);
    }

    public ListViewVerticalScrollTracker(final Trackable list){
        this.list = list;
    }

    public int calculateDeltaYFromScrollPositions(int firstVisiblePosition, int visibleItemCount){
        SparseArray<Integer> previousPositions = positions;

        positions = new SparseArray<Integer>(visibleItemCount);
        for(int i = 0; i < visibleItemCount; i++){
            positions.put(firstVisiblePosition + i, list.getChildOffset(i));
        }

        if(previousPositions != null){
            for(int i = 0; i < previousPositions.size(); i++){
                int position = previousPositions.keyAt(i);
                int previousTop = previousPositions.get(position);
                Integer newTop = positions.get(position);
                if(newTop != null){
                    return newTop - previousTop;
                }
            }
        }
        return 0;
    }
}

package com.dmccartney.android.scrollables;


import android.util.SparseArray;
import android.widget.AbsListView;

/*
 * This tracks change in the vertical scroll position of a ListView.
 * It gives you the "deltaY" of your scroll position by tracking the ongoing top-position changes of
 * visible children.
 *
 * Usage: call #calculateDeltaYFromScrollPositions in your OnScrollListener to both track the new
 *        scroll position, and to receive the change in vertical scroll position (deltaY).
 */
public class ListViewVerticalScrollTracker {
    private final AbsListView list;
    private SparseArray<Integer> positions;

    public ListViewVerticalScrollTracker(final AbsListView listView){
        list = listView;
    }

    public int calculateDeltaYFromScrollPositions(int firstVisiblePosition, int visibleItemCount){
        SparseArray<Integer> previousPositions = positions;

        positions = new SparseArray<Integer>(visibleItemCount);
        for(int i = 0; i < visibleItemCount; i++){
            positions.put(firstVisiblePosition + i, list.getChildAt(i).getTop());
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

    public void clear(){
        positions = null;
    }
}

package com.dmccartney.android.scrollables;


import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.google.common.collect.Iterables.cycle;
import static com.google.common.collect.Iterables.limit;

public class ListViewActivity extends Activity {

    @InjectView(R.id.list_view_sample) ListView listView;
    @InjectView(R.id.list_view_info_first) TextView infoFirstVisible;
    @InjectView(R.id.list_view_info_last) TextView infoLastVisible;
    @InjectView(R.id.list_view_info_delta) TextView infoDelta;
    @InjectView(R.id.list_view_info_delta_smooth) TextView infoDeltaSmooth;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        ButterKnife.inject(this);

        listView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                ImmutableList.copyOf(limit(cycle("A", "B", "C", "D", "E"), 40))));

        // showing how we can add multiple scroll listeners
        listView.addOnScrollListener(new AbsListView.OnScrollListener() {
            @Override public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                infoFirstVisible.setText("first: #" + firstVisibleItem);
            }
        });
        listView.addOnScrollListener(new AbsListView.OnScrollListener() {
            @Override public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                infoLastVisible.setText("last: #" + firstVisibleItem + visibleItemCount);
            }
        });

        // showing what you can do with a deltaY listener
        listView.addOnScrollDeltaYListener(new ListView.OnScrollDeltaYListener() {
            @Override public void onScrollDeltaY(int deltaY) {
                toggleExpandCollapseIcon(deltaY > 0, infoDelta);
                infoDelta.setText("delta: " + deltaY + " px");
            }
        });
        listView.addOnScrollDeltaYListenerSmoothed(new ListView.OnScrollDeltaYListener() {
            @Override public void onScrollDeltaY(int deltaY) {
                toggleExpandCollapseIcon(deltaY > 0, infoDeltaSmooth);
                infoDeltaSmooth.setText("smooth delta: " + deltaY + " px");
            }
        });

    }

    private void toggleExpandCollapseIcon(boolean toggle, TextView textView) {
        textView.setCompoundDrawablesWithIntrinsicBounds(
                toggle ? R.drawable.ic_action_collapse
                       : R.drawable.ic_action_expand, 0, 0, 0);
    }
}

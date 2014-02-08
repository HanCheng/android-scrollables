package com.dmccartney.android.scrollables;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScrollViewActivity extends Activity {

    @InjectView(R.id.scroll_view_sample) ScrollView scrollView;
    @InjectView(R.id.scroll_view_info) TextView info;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view);
        ButterKnife.inject(this);

        scrollView.addOnScrollChangedListener(new ScrollView.OnScrollChangedListener() {
            @Override public void onScrollChanged(ScrollView view, int x, int y, int oldX, int oldY) {
                info.setText("(" + x + ", " + y + ")");
            }
        });
    }
}


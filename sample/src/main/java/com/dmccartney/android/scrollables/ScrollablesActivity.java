package com.dmccartney.android.scrollables;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollablesActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollables);
        ButterKnife.inject(this);
    }

    Map<Integer, Class<? extends Activity>> BTN_TO_CLAZZ = ImmutableMap.of(
            R.id.scrollables_scroll_view_btn, ScrollViewActivity.class,
            R.id.scrollables_list_view_btn, ListViewActivity.class
    );
    @OnClick({R.id.scrollables_list_view_btn,
              R.id.scrollables_scroll_view_btn}) void onButtonClick(View view) {
        startActivity(new Intent(this, BTN_TO_CLAZZ.get(view.getId())));
    }

}


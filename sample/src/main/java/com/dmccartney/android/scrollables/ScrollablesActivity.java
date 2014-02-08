package com.dmccartney.android.scrollables;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScrollablesActivity extends Activity {

    @InjectView(R.id.sample) Scrollables sample;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);
        ButterKnife.inject(this);

        // sample ...
    }
}


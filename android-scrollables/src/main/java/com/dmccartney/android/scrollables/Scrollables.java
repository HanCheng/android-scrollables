package com.dmccartney.android.scrollables;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class Scrollables extends View {

    public Scrollables(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();

        if( ! isInEditMode()) {
        
        }
    }
}


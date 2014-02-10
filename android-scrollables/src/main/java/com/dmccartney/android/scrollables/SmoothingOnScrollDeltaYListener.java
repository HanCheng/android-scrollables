package com.dmccartney.android.scrollables;

import static java.lang.Math.abs;

public class SmoothingOnScrollDeltaYListener implements ListView.OnScrollDeltaYListener {

    public static final int DEFAULT_SMOOTHING_THRESHOLD = 25;

    private final ListView.OnScrollDeltaYListener wrappedListener;
    private final int smoothingThreshold;

    private int accumulatedDeltaY;

    public SmoothingOnScrollDeltaYListener(ListView.OnScrollDeltaYListener wrappedListener, int smoothingThreshold) {
        this.wrappedListener = wrappedListener;
        this.smoothingThreshold = smoothingThreshold;
        this.accumulatedDeltaY = 0;
    }

    @Override public void onScrollDeltaY(int deltaY) {
        accumulatedDeltaY = accumulatedDeltaY + deltaY;
        if(abs(accumulatedDeltaY) > smoothingThreshold) {
            wrappedListener.onScrollDeltaY(accumulatedDeltaY);
            accumulatedDeltaY = 0;
        }
    }

    public static ListView.OnScrollDeltaYListener wrappingListener(ListView.OnScrollDeltaYListener listener) {
        return wrappingListenerToThreshold(listener, DEFAULT_SMOOTHING_THRESHOLD);
    }

    public static ListView.OnScrollDeltaYListener wrappingListenerToThreshold(ListView.OnScrollDeltaYListener listener, int smoothingThreshold) {
        return new SmoothingOnScrollDeltaYListener(listener, smoothingThreshold);
    }
}

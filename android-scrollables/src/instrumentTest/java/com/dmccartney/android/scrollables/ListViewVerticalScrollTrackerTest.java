package com.dmccartney.android.scrollables;

import android.test.InstrumentationTestCase;

import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ListViewVerticalScrollTrackerTest extends InstrumentationTestCase {

    @Mock ListViewVerticalScrollTracker.Trackable list;

    ListViewVerticalScrollTracker tracker;

    public void setUp() throws Exception {
        super.setUp();
        System.setProperty( "dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath() );
        initMocks(this);
        tracker = new ListViewVerticalScrollTracker(list);
    }

    public void testGivenTheListIsEmptyTheFirstDeltaShouldBeZero() throws Exception {
        int deltaY = tracker.calculateDeltaYFromScrollPositions(0, 0);

        assertThat(deltaY).isEqualTo(0);
    }

    public void testWhenTheresOneItemItsDeltaShouldBeZeroAtFirst() throws Exception {
        int FIRST = 10;
        int SECOND = 20;
        when(list.getChildOffset(0)).thenReturn(FIRST, SECOND);

        int deltaY = tracker.calculateDeltaYFromScrollPositions(0, 1);

        assertThat(deltaY).isEqualTo(0);
    }

    public void testWhenTheresOneItemItsDeltaShouldBeUsed() throws Exception {
        int DELTA = 100;
        when(list.getChildOffset(0)).thenReturn(10, 10 + DELTA);

        int firstDelta = tracker.calculateDeltaYFromScrollPositions(0, 1);
        int secondDelta = tracker.calculateDeltaYFromScrollPositions(0, 1);

        assertThat(secondDelta).isEqualTo(DELTA);
    }

    public void testWhenItsTheSameSetOfVisibleItemsItShouldReceiveTheDelta() throws Exception {
        int DELTA = 100,
            ITEM_A_OFFSET = 15,
            ITEM_B_OFFSET = 30;
        // same set of visible items
        when(list.getChildOffset(0)).thenReturn(ITEM_A_OFFSET, ITEM_A_OFFSET + DELTA);
        when(list.getChildOffset(1)).thenReturn(ITEM_B_OFFSET, ITEM_B_OFFSET + DELTA);

        int firstDelta = tracker.calculateDeltaYFromScrollPositions(0, 2);
        int secondDelta = tracker.calculateDeltaYFromScrollPositions(0, 2);

        assertThat(secondDelta).isEqualTo(DELTA);
    }

    public void testWhenTheSetOfVisibleItemsChangesItStillGetsTheDelta() throws Exception {
        int DELTA = 100,
            ITEM_A_OFFSET = 15,
            ITEM_B_OFFSET = 30;
        // changing set of visible items (item B moves into the first child slot)
        when(list.getChildOffset(0)).thenReturn(ITEM_A_OFFSET, ITEM_B_OFFSET + DELTA);
        when(list.getChildOffset(1)).thenReturn(ITEM_B_OFFSET);

        // so between calculations, the first visible item changes, (the first item scrolled out of view)
        int firstDelta = tracker.calculateDeltaYFromScrollPositions(0, 2);
        int secondDelta = tracker.calculateDeltaYFromScrollPositions(1, 1);

        assertThat(secondDelta).isEqualTo(DELTA);
    }

}

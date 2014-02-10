# Android Scrollables Library

This contains miscellaneous scrolling tools that I've found useful in my work.

## `com.dmccartney.android.ListView`
This is an extension of the default `android.widget.ListView`.
It supports multiple `OnScrollListeners` 
```java
  // add as many scroll listeners as you please
  listView.addOnScrollListener(new AbsListView.OnScrollListener() { ... });
  listView.addOnScrollListener(new AbsListView.OnScrollListener() { ... });      
```

It also supports tracking the vertical scroll changes using `OnScrollDeltaYListeners`
```java
  // track how much they're scrolling
  listView.addOnScrollDeltaYListener(new ListView.OnScrollDeltaYListener() {
    @Override public void onScrollDeltaY(int deltaY) {
      // they scrolled deltaY pixels
    }
  });
```

Since the deltaY tracking tends to be jumpy, it also supports smoothing out the vertical scroll changes so the delta's will always be larger (and less frequent)
```java
  // track how much they're scrolling (this will be less jumpy than the unsmoothed version)
  listView.addOnScrollDeltaYListenerSmoothed(new ListView.OnScrollDeltaYListener() {
    @Override public void onScrollDeltaY(int deltaY) {
      // they scrolled deltaY pixels
    }
  });
```

## `com.dmccartney.android.ScrollView`
This is an extension of the default `android.widget.ScrollView`.
It supports multiple `OnScrollChangedListeners`
```java
  // add as many scroll change listeners as you please
  scrollView.addOnScrollChangedListener(new ScrollView.OnScrollChangedListener() {
    @Override public void onScrollChanged(ScrollView view, int x, int y, int oldX, int oldY) {
      // ...
    }
  });
```

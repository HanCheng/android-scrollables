// Generated code from Butter Knife. Do not modify!
package com.dmccartney.android.scrollables;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ScrollViewActivity$$ViewInjector {
  public static void inject(Finder finder, final com.dmccartney.android.scrollables.ScrollViewActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131165249);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165249' for field 'scrollView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.scrollView = (com.dmccartney.android.scrollables.ScrollView) view;
    view = finder.findById(source, 2131165250);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165250' for field 'info' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.info = (android.widget.TextView) view;
  }

  public static void reset(com.dmccartney.android.scrollables.ScrollViewActivity target) {
    target.scrollView = null;
    target.info = null;
  }
}

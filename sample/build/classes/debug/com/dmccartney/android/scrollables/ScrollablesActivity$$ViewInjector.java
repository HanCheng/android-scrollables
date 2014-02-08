// Generated code from Butter Knife. Do not modify!
package com.dmccartney.android.scrollables;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ScrollablesActivity$$ViewInjector {
  public static void inject(Finder finder, final com.dmccartney.android.scrollables.ScrollablesActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131165251);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165251' for method 'onButtonClick' was not found. If this view is optional add '@Optional' annotation.");
    }
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onButtonClick(p0);
        }
      });
    view = finder.findById(source, 2131165252);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165252' for method 'onButtonClick' was not found. If this view is optional add '@Optional' annotation.");
    }
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onButtonClick(p0);
        }
      });
  }

  public static void reset(com.dmccartney.android.scrollables.ScrollablesActivity target) {
  }
}

// Generated code from Butter Knife. Do not modify!
package com.dmccartney.android.scrollables;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ListViewActivity$$ViewInjector {
  public static void inject(Finder finder, final com.dmccartney.android.scrollables.ListViewActivity target, Object source) {
    View view;
    view = finder.findById(source, 2131165244);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165244' for field 'listView' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.listView = (com.dmccartney.android.scrollables.ListView) view;
    view = finder.findById(source, 2131165245);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165245' for field 'infoFirstVisible' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.infoFirstVisible = (android.widget.TextView) view;
    view = finder.findById(source, 2131165248);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165248' for field 'infoLastVisible' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.infoLastVisible = (android.widget.TextView) view;
    view = finder.findById(source, 2131165246);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165246' for field 'infoDelta' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.infoDelta = (android.widget.TextView) view;
    view = finder.findById(source, 2131165247);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131165247' for field 'infoDeltaSmooth' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.infoDeltaSmooth = (android.widget.TextView) view;
  }

  public static void reset(com.dmccartney.android.scrollables.ListViewActivity target) {
    target.listView = null;
    target.infoFirstVisible = null;
    target.infoLastVisible = null;
    target.infoDelta = null;
    target.infoDeltaSmooth = null;
  }
}

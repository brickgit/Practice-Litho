package com.brickgit.kata.litho.menu;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

import java.util.Locale;

/** Created by Daniel Lin on 2018/10/1. */
@LayoutSpec
public class MenuItemSpec {

  @OnCreateLayout
  static Component onCreateLayout(
      ComponentContext c, @Prop final MenuModel model, @Prop int index) {
    return Column.create(c)
        .paddingDip(YogaEdge.ALL, 16)
        .backgroundColor(index % 2 == 0 ? Color.WHITE : Color.LTGRAY)
        .child(
            Text.create(c)
                .text(String.format(Locale.getDefault(), "%d. %s", index + 1, model.getTitle()))
                .textSizeSp(18)
                .build())
        .clickHandler(MenuItem.onClick(c))
        .build();
  }

  @OnEvent(ClickEvent.class)
  static void onClick(
      ComponentContext c,
      @FromEvent View view,
      @Prop final MenuModel model,
      @Prop final int[] currentIndices) {
    final Intent intent =
        new Intent(c, model.getModels() == null ? model.getCls() : MenuActivity.class);
    intent.putExtra(MenuActivity.INDICES, currentIndices);
    c.startActivity(intent);
  }
}

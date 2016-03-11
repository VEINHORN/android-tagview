package com.veinhorn.tagview.drawers;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.veinhorn.tagview.TagView;

/**
 * Created by veinhorn on 11.3.16.
 */
public interface TagDrawer {
    // add java docs for this method
    void drawTag(Rect bounds, Canvas canvas, TagView.TagViewData data);
}

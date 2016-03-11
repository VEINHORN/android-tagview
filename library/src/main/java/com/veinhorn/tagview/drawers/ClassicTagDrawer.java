package com.veinhorn.tagview.drawers;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.veinhorn.tagview.TagView;
import com.veinhorn.tagview.Utils;

/**
 * Created by veinhorn on 11.3.16.
 */
public class ClassicTagDrawer implements TagDrawer {
    @Override
    public void drawTag(Rect bounds, Canvas canvas, TagView.TagViewData data) {
        RectF rect = Utils.toRectF(bounds);
        canvas.drawRoundRect(rect, data.tagBorderRadius, data.tagBorderRadius, data.backgroundPaint);
    }
}

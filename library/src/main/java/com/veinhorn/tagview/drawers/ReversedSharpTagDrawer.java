package com.veinhorn.tagview.drawers;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

import com.veinhorn.tagview.TagView;
import com.veinhorn.tagview.Utils;

import static com.veinhorn.tagview.Tags.*;

/**
 * Created by veinhorn on 11.3.16.
 */
public class ReversedSharpTagDrawer implements TagDrawer {
    @Override
    public void drawTag(Rect bounds, Canvas canvas, TagView.TagViewData data) {
        RectF formattedBounds = Utils.toRectF(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.left += data.tagRightPadding * SHARP_TAG_MULTIPLIER;
        float halfOfRectHeight= (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, data.backgroundPaint);
        Path trianglePath = createTrianglePath(data, rect, halfOfRectHeight);
        canvas.drawPath(trianglePath, data.trianglePaint);
    }

    protected Path createTrianglePath(TagView.TagViewData data, RectF rect, float halfOfRectHeight) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(rect.left, rect.top);
        path.lineTo(rect.left - data.tagLeftPadding * SHARP_TAG_MULTIPLIER, halfOfRectHeight);
        path.lineTo(rect.left, rect.bottom);
        path.lineTo(rect.left, rect.top);
        return path;
    }
}

package com.veinhorn.tagview.drawers;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

import com.veinhorn.tagview.TagView;
import com.veinhorn.tagview.Utils;

import static com.veinhorn.tagview.Tags.SHARP_TAG_MULTIPLIER;

/**
 * Created by veinhorn on 11.3.16.
 */
public class ReversedModernSharpTagDrawer extends ReversedSharpTagDrawer {
    @Override
    public void drawTag(Rect bounds, Canvas canvas, TagView.TagViewData data) {
        RectF formattedBounds = Utils.toRectF(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.left += data.tagRightPadding * SHARP_TAG_MULTIPLIER;
        float halfOfRectHeight = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, data.backgroundPaint);
        float xPos = formattedBounds.right - data.tagRightPadding;
        float yPos = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPos, yPos, data.tagCircleRadius, data.circlePaint);
        Path trianglePath = createTrianglePath(data, rect, halfOfRectHeight);
        canvas.drawPath(trianglePath, data.trianglePaint);
    }
}

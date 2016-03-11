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
public class ModernSharpTagDrawer extends SharpTagDrawer {
    @Override
    public void drawTag(Rect bounds, Canvas canvas, TagView.TagViewData data) {
        RectF formattedBounds = Utils.toRectF(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.right -= data.tagRightPadding * SHARP_TAG_MULTIPLIER;
        canvas.drawRect(rect, data.backgroundPaint);
        float xPos = formattedBounds.left + data.tagLeftPadding;
        float yPos = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPos, yPos, data.tagCircleRadius, data.circlePaint);
        float halfOfRectHeight = (rect.bottom - rect.top) / 2;
        Path trianglePath = createTrianglePath(data, rect, halfOfRectHeight);
        canvas.drawPath(trianglePath, data.trianglePaint);
    }
}

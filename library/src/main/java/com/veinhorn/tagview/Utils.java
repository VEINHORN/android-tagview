package com.veinhorn.tagview;

import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by veinhorn on 11.3.16.
 */
public class Utils {
    /**
     * Converts Rect object to RectF
     * @param rect
     * @return
     */
    public static RectF toRectF(Rect rect) {
        return new RectF(rect.left, rect.top, rect.right, rect.bottom);
    }
}

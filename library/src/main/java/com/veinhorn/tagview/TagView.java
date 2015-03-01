package com.veinhorn.tagview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by veinhorn on 1.3.15.
 */

public class TagView extends TextView {
    private int leftPadding = 15;
    private int rightPadding = 15;
    private int topPadding = 10;
    private int bottomPadding = 10;
    private static final int PADDING = 10;

    private Paint backgroundPaint;
    private Paint circlePaint;

    private float borderRadius = 5;
    private float circleRadius = 5;

    private int tagType = 0;

    public static final int CLASSIC = 0;
    public static final int MODERN = 1;

    private static int TEXT_COLOR = Color.WHITE;

    private class TagDrawable extends Drawable {
        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void draw(Canvas canvas) {
            if(tagType == CLASSIC) drawClassicTag(getBounds(), canvas);
            else if(tagType == MODERN) drawModernTag(getBounds(), canvas);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    }

    public TagView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TagView, 0, 0);
        try {
            tagType = typedArray.getInteger(R.styleable.TagView_tagType, CLASSIC);
        } finally {
            typedArray.recycle();
        }

        init();
    }

    private void drawClassicTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, borderRadius, borderRadius, backgroundPaint);
        setTextColor(TEXT_COLOR);
    }

    private void drawModernTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding + 5, topPadding, rightPadding, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, borderRadius, borderRadius, backgroundPaint);
        float xPosition = formattedBounds.left + PADDING;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, circleRadius, circlePaint);
        setTextColor(TEXT_COLOR);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(new TagDrawable());
        } else if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(new TagDrawable());
        }
    }

    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.RED);

        circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
    }

    private RectF getBoundsForText(Rect bounds) {
        return new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }
}
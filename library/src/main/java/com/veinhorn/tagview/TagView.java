package com.veinhorn.tagview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
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

    private static final int TEXT_COLOR_DEFAULT = Color.WHITE;
    private static final int CIRCLE_COLOR_DEFAULT = Color.WHITE;
    private static final int BORDER_RADIUS_DEFAULT = 5;
    private static final int CIRCLE_RADIUS_DEFAULT = 7;

    private Paint backgroundPaint;
    private Paint circlePaint;
    private Paint trianglePaint;

    // TagView properties
    private int tagType;
    private int tagColor;
    private boolean tagUpperCase;
    private float tagBorderRadius;
    private float tagCircleRadius;
    private int tagCircleColor;
    private int tagTextColor;
    //////////////////

    public static final int CLASSIC = 0;
    public static final int MODERN = 1;
    public static final int TRAPEZIUM = 2;
    public static final int MODERN_TRAPEZIUM = 3;
    public static final int MODERN_REVERSED = 4;
    public static final int TRAPEZIUM_REVERSED = 5;
    public static final int MODERN_TRAPEZIUM_REVERSED = 6;

    private class TagDrawable extends Drawable {
        @Override
        public void setAlpha(int alpha) {}

        @Override
        public void draw(Canvas canvas) {
            if(tagType == CLASSIC) drawClassicTag(getBounds(), canvas);
            else if(tagType == MODERN) drawModernTag(getBounds(), canvas);
            else if(tagType == TRAPEZIUM) drawTrapeziumTag(getBounds(), canvas);
            else if(tagType == MODERN_TRAPEZIUM) drawModernTrapeziumTag(getBounds(), canvas);
            else if(tagType == MODERN_REVERSED) drawModernReversedTag(getBounds(), canvas);
            else if(tagType == TRAPEZIUM_REVERSED) drawTrapeziumReversedTag(getBounds(), canvas);
            else if(tagType == MODERN_TRAPEZIUM_REVERSED) drawModernTrapeziumReversedTag(getBounds(), canvas);
            else drawClassicTag(getBounds(), canvas);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {}

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
            tagColor = typedArray.getColor(R.styleable.TagView_tagColor, Color.BLACK);
            tagUpperCase = typedArray.getBoolean(R.styleable.TagView_tagUpperCase, false);
            tagBorderRadius = typedArray.getInteger(R.styleable.TagView_tagBorderRadius, BORDER_RADIUS_DEFAULT);
            tagCircleRadius = typedArray.getInteger(R.styleable.TagView_tagCircleRadius, CIRCLE_RADIUS_DEFAULT);
            tagCircleColor = typedArray.getColor(R.styleable.TagView_tagCircleColor, CIRCLE_COLOR_DEFAULT);
            tagTextColor = typedArray.getColor(R.styleable.TagView_tagTextColor, TEXT_COLOR_DEFAULT);
        } finally {
            typedArray.recycle();
        }

        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(tagUpperCase) setText(getText().toString().toUpperCase());
        super.onDraw(canvas);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(new TagDrawable());
        } else if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(new TagDrawable());
        }
    }

    private void init() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(tagColor);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(tagCircleColor);

        trianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        trianglePaint.setColor(tagColor);
        trianglePaint.setStyle(Paint.Style.FILL);
    }

    private RectF getBoundsForText(Rect bounds) {
        return new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    private void drawClassicTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, tagBorderRadius, tagBorderRadius, backgroundPaint);
        setTextColor(tagTextColor);
    }

    private void drawModernTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding * 2, topPadding, rightPadding, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, tagBorderRadius, tagBorderRadius, backgroundPaint);
        float xPosition = formattedBounds.left + leftPadding;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, tagCircleRadius, circlePaint);
        setTextColor(tagTextColor);
    }

    private void drawTrapeziumTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding, topPadding, rightPadding * 3, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.right -= rightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        Path trianglePath = getTrianglePath(rect, y);
        canvas.drawPath(trianglePath, trianglePaint);
        setTextColor(tagTextColor);
    }

    private void drawModernTrapeziumTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding * 2, topPadding, rightPadding * 3, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.right -= rightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        float xPosition = formattedBounds.left + leftPadding;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, tagCircleRadius, circlePaint);
        Path trianglePath = getTrianglePath(rect, y);
        canvas.drawPath(trianglePath, trianglePaint);
        setTextColor(tagTextColor);
    }

    private void drawModernReversedTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding, topPadding, rightPadding * 2, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, tagBorderRadius, tagBorderRadius, backgroundPaint);
        float xPosition = formattedBounds.right - rightPadding;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, tagCircleRadius, circlePaint);
        setTextColor(tagTextColor);
    }

    private void drawTrapeziumReversedTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding * 3, topPadding, rightPadding, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.left += rightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        Path trianglePath = getReversedTrianglePath(rect, y);
        canvas.drawPath(trianglePath, trianglePaint);
        setTextColor(tagTextColor);
    }

    private void drawModernTrapeziumReversedTag(Rect bounds, Canvas canvas) {
        setPadding(leftPadding * 3, topPadding, rightPadding * 2, bottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.left += rightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        float xPosition = formattedBounds.right - rightPadding;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, tagCircleRadius, circlePaint);
        Path trianglePath = getReversedTrianglePath(rect, y);
        canvas.drawPath(trianglePath, trianglePaint);
        setTextColor(tagTextColor);
    }

    private Path getTrianglePath(RectF rect, float y) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(rect.right, rect.top);
        path.lineTo(rect.right + rightPadding * 3, y);
        path.lineTo(rect.right, rect.bottom);
        path.lineTo(rect.right, rect.top);
        return path;
    }

    private Path getReversedTrianglePath(RectF rect, float y) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(rect.left, rect.top);
        path.lineTo(rect.left - leftPadding * 3, y);
        path.lineTo(rect.left, rect.bottom);
        path.lineTo(rect.left, rect.top);
        return path;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
        invalidate();
        requestLayout();
    }

    public int getTagColor() {
        return tagColor;
    }

    public void setTagColor(int tagColor) {
        this.tagColor = tagColor;
        init();
        invalidate();
        requestLayout();
    }

    public boolean isTagUpperCase() {
        return tagUpperCase;
    }

    public void setTagUpperCase(boolean tagUpperCase) {
        this.tagUpperCase = tagUpperCase;
        invalidate();
        requestLayout();
    }

    public float getTagBorderRadius() {
        return tagBorderRadius;
    }

    public void setTagBorderRadius(int tagBorderRadius) {
        this.tagBorderRadius = tagBorderRadius;
        invalidate();
        requestLayout();
    }

    public float getTagCircleRadius() {
        return  tagCircleRadius;
    }

    public void setTagCircleRadius(float tagCircleRadius) {
        this.tagCircleRadius = tagCircleRadius;
        invalidate();
        requestLayout();
    }

    public int getTagCircleColor() {
        return tagCircleColor;
    }

    public void setTagCircleColor(int tagCircleColor) {
        this.tagCircleColor = tagCircleColor;
        init();
        invalidate();
        requestLayout();
    }

    public int getTagTextColor() {
        return tagTextColor;
    }

    public void setTagTextColor(int tagTextColor) {
        this.tagTextColor = tagTextColor;
        invalidate();
        requestLayout();
    }
}
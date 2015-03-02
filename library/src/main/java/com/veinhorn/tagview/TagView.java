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
    private static final int LEFT_PADDING_DEFAULT = 15;
    private static final int RIGHT_PADDING_DEFAULT = 15;
    private static final int TOP_PADDING_DEFAULT = 10;
    private static final int BOTTOM_PADDING_DEFAULT = 10;
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

    private int tagLeftPadding;
    private int tagRightPadding;
    private int tagTopPadding;
    private int tagBottomPadding;

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
        initPadding();
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

    private void initPadding() {
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();
        int bottom = getPaddingBottom();
        if(left == 0) tagLeftPadding = LEFT_PADDING_DEFAULT;
        else tagLeftPadding = left;
        if(right == 0) tagRightPadding = RIGHT_PADDING_DEFAULT;
        else tagRightPadding = right;
        if(top == 0) tagTopPadding = TOP_PADDING_DEFAULT;
        else tagTopPadding = top;
        if(bottom == 0) tagBottomPadding = BOTTOM_PADDING_DEFAULT;
        else tagBottomPadding = bottom;
    }

    private RectF getBoundsForText(Rect bounds) {
        return new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    private void drawClassicTag(Rect bounds, Canvas canvas) {
        setPadding(tagLeftPadding, tagTopPadding, tagRightPadding, tagBottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, tagBorderRadius, tagBorderRadius, backgroundPaint);
        setTextColor(tagTextColor);
    }

    private void drawModernTag(Rect bounds, Canvas canvas) {
        setPadding(tagLeftPadding * 2, tagTopPadding, tagRightPadding, tagBottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, tagBorderRadius, tagBorderRadius, backgroundPaint);
        float xPosition = formattedBounds.left + tagLeftPadding;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, tagCircleRadius, circlePaint);
        setTextColor(tagTextColor);
    }

    private void drawTrapeziumTag(Rect bounds, Canvas canvas) {
        setPadding(tagLeftPadding, tagTopPadding, tagRightPadding * 3, tagBottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.right -= tagRightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        Path trianglePath = getTrianglePath(rect, y);
        canvas.drawPath(trianglePath, trianglePaint);
        setTextColor(tagTextColor);
    }

    private void drawModernTrapeziumTag(Rect bounds, Canvas canvas) {
        setPadding(tagLeftPadding * 2, tagTopPadding, tagRightPadding * 3, tagBottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.right -= tagRightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        float xPosition = formattedBounds.left + tagLeftPadding;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, tagCircleRadius, circlePaint);
        Path trianglePath = getTrianglePath(rect, y);
        canvas.drawPath(trianglePath, trianglePaint);
        setTextColor(tagTextColor);
    }

    private void drawModernReversedTag(Rect bounds, Canvas canvas) {
        setPadding(tagLeftPadding, tagTopPadding, tagRightPadding * 2, tagBottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        canvas.drawRoundRect(formattedBounds, tagBorderRadius, tagBorderRadius, backgroundPaint);
        float xPosition = formattedBounds.right - tagRightPadding;
        float yPosition = (formattedBounds.bottom - formattedBounds.top) / 2;
        canvas.drawCircle(xPosition, yPosition, tagCircleRadius, circlePaint);
        setTextColor(tagTextColor);
    }

    private void drawTrapeziumReversedTag(Rect bounds, Canvas canvas) {
        setPadding(tagLeftPadding * 3, tagTopPadding, tagRightPadding, tagBottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.left += tagRightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        Path trianglePath = getReversedTrianglePath(rect, y);
        canvas.drawPath(trianglePath, trianglePaint);
        setTextColor(tagTextColor);
    }

    private void drawModernTrapeziumReversedTag(Rect bounds, Canvas canvas) {
        setPadding(tagLeftPadding * 3, tagTopPadding, tagRightPadding * 2, tagBottomPadding);
        RectF formattedBounds = getBoundsForText(bounds);
        RectF rect = new RectF(formattedBounds);
        rect.left += tagRightPadding * 3;
        float y = (rect.bottom - rect.top) / 2;
        canvas.drawRect(rect, backgroundPaint);
        float xPosition = formattedBounds.right - tagRightPadding;
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
        path.lineTo(rect.right + tagRightPadding * 3, y);
        path.lineTo(rect.right, rect.bottom);
        path.lineTo(rect.right, rect.top);
        return path;
    }

    private Path getReversedTrianglePath(RectF rect, float y) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(rect.left, rect.top);
        path.lineTo(rect.left - tagLeftPadding * 3, y);
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
    
    public int getTagLeftPadding() {
        return tagLeftPadding;
    }

    public void setTagLeftPadding(int tagLeftPadding) {
        this.tagLeftPadding = tagLeftPadding;
        invalidate();
        requestLayout();
    }

    public int getTagRightPadding() {
        return tagRightPadding;
    }

    public void setTagRightPadding(int tagRightPadding) {
        this.tagRightPadding = tagRightPadding;
        invalidate();
        requestLayout();
    }

    public int getTagTopPadding() {
        return tagTopPadding;
    }

    public void setTagTopPadding(int tagTopPadding) {
        this.tagTopPadding = tagTopPadding;
        invalidate();
        requestLayout();
    }

    public int getTagBottomPadding() {
        return tagBottomPadding;
    }

    public void setTagBottomPadding(int tagBottomPadding) {
        this.tagBottomPadding = tagBottomPadding;
        invalidate();
        requestLayout();

    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        setPadding(left, top, right, bottom);
        this.tagLeftPadding = left;
        this.tagRightPadding = right;
        this.tagTopPadding = top;
        this.tagBottomPadding = bottom;
    }
}
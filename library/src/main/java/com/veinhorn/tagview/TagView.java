package com.veinhorn.tagview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.veinhorn.tagview.drawers.ClassicTagDrawer;
import com.veinhorn.tagview.drawers.ModernSharpTagDrawer;
import com.veinhorn.tagview.drawers.ModernTagDrawer;
import com.veinhorn.tagview.drawers.ReversedModernSharpTagDrawer;
import com.veinhorn.tagview.drawers.ReversedModernTagDrawer;
import com.veinhorn.tagview.drawers.ReversedSharpTagDrawer;
import com.veinhorn.tagview.drawers.SharpTagDrawer;

import static com.veinhorn.tagview.Tags.*;

/**
 * Created by veinhorn on 1.3.15.
 */

public class TagView extends TextView {
    public class TagViewData {
        // TagView properties
        public int tagType;
        public int tagColor;
        public int tagTextColor;
        public boolean tagUpperCase;
        public float tagBorderRadius;
        public float tagCircleRadius;
        public int tagCircleColor;

        public int tagLeftPadding;
        public int tagRightPadding;
        private int tagTopPadding;
        private int tagBottomPadding;
        // Paint object for drawing various types of tags
        public Paint backgroundPaint;
        public Paint circlePaint;
        public Paint trianglePaint;
    }
    private TagViewData data;

    private class TagDrawable extends Drawable {
        @Override
        public void setAlpha(int alpha) {}

        // TODO: Replace with case statement
        @Override
        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            if (data.tagType == CLASSIC) {
                setPadding(data.tagLeftPadding, data.tagTopPadding,
                        data.tagRightPadding, data.tagBottomPadding);
                new ClassicTagDrawer().drawTag(bounds, canvas, data);
            } else if (data.tagType == MODERN) {
                setPadding(data.tagLeftPadding * MODERN_TAG_MULTIPLIER, data.tagTopPadding,
                        data.tagRightPadding, data.tagBottomPadding);
                new ModernTagDrawer().drawTag(bounds, canvas, data);
            } else if (data.tagType == REVERSED_MODERN) {
                setPadding(data.tagLeftPadding, data.tagTopPadding,
                        data.tagRightPadding * MODERN_TAG_MULTIPLIER, data.tagBottomPadding);
                new ReversedModernTagDrawer().drawTag(bounds, canvas, data);
            } else if (data.tagType == SHARP) {
                setPadding(data.tagLeftPadding, data.tagTopPadding,
                        data.tagRightPadding * SHARP_TAG_MULTIPLIER, data.tagBottomPadding);
                new SharpTagDrawer().drawTag(bounds, canvas, data);
            } else if (data.tagType == REVERSED_SHARP) {
                setPadding(data.tagLeftPadding * SHARP_TAG_MULTIPLIER, data.tagTopPadding,
                        data.tagRightPadding, data.tagBottomPadding);
                new ReversedSharpTagDrawer().drawTag(bounds, canvas, data);
            } else if (data.tagType == MODERN_SHARP) {
                setPadding(data.tagLeftPadding * MODERN_TAG_MULTIPLIER, data.tagTopPadding,
                        data.tagRightPadding * SHARP_TAG_MULTIPLIER, data.tagBottomPadding);
                new ModernSharpTagDrawer().drawTag(bounds, canvas, data);
            } else if (data.tagType == REVERSED_MODERN_SHARP) {
                setPadding(data.tagLeftPadding * SHARP_TAG_MULTIPLIER, data.tagTopPadding,
                        data.tagRightPadding * MODERN_TAG_MULTIPLIER, data.tagBottomPadding);
                new ReversedModernSharpTagDrawer().drawTag(bounds, canvas, data);
            }
            setTextColor(data.tagTextColor);
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
        data = new TagViewData();
        try {
            data.tagType = typedArray.getInteger(R.styleable.TagView_tagType, CLASSIC);
            data.tagColor = typedArray.getColor(R.styleable.TagView_tagColor, Color.BLACK);
            data.tagUpperCase = typedArray.getBoolean(R.styleable.TagView_tagUpperCase, false);
            data.tagBorderRadius = typedArray.getInteger(R.styleable.TagView_tagBorderRadius, BORDER_RADIUS_DEFAULT);
            data.tagCircleRadius = typedArray.getInteger(R.styleable.TagView_tagCircleRadius, CIRCLE_RADIUS_DEFAULT);
            data.tagCircleColor = typedArray.getColor(R.styleable.TagView_tagCircleColor, CIRCLE_COLOR_DEFAULT);
            data.tagTextColor = typedArray.getColor(R.styleable.TagView_tagTextColor, TEXT_COLOR_DEFAULT);
        } finally {
            typedArray.recycle();
        }

        initPadding();
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(data.tagUpperCase) setText(getText().toString().toUpperCase());
        super.onDraw(canvas);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(new TagDrawable());
        } else if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(new TagDrawable());
        }
    }

    /**
     * Initializes Paint objects that will be used to draw tags, speed up draw method
     */
    private void init() {
        data.backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        data.backgroundPaint.setColor(data.tagColor);

        data.circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        data.circlePaint.setColor(data.tagCircleColor);

        data.trianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        data.trianglePaint.setColor(data.tagColor);
        data.trianglePaint.setStyle(Paint.Style.FILL);
    }

    /**
     * Set default values for padding, if you not specify it in xml layout
     */
    private void initPadding() {
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();
        int bottom = getPaddingBottom();
        if(left == 0) data.tagLeftPadding = LEFT_PADDING_DEFAULT;
        else data.tagLeftPadding = left;
        if(right == 0) data.tagRightPadding = RIGHT_PADDING_DEFAULT;
        else data.tagRightPadding = right;
        if(top == 0) data.tagTopPadding = TOP_PADDING_DEFAULT;
        else data.tagTopPadding = top;
        if(bottom == 0) data.tagBottomPadding = BOTTOM_PADDING_DEFAULT;
        else data.tagBottomPadding = bottom;
    }

    public int getTagType() {
        return data.tagType;
    }

    public void setTagType(int tagType) {
        data.tagType = tagType;
        invalidate();
        requestLayout();
    }

    public int getTagColor() {
        return data.tagColor;
    }

    public void setTagColor(int tagColor) {
        data.tagColor = tagColor;
        init();
        invalidate();
        requestLayout();
    }

    public boolean isTagUpperCase() {
        return data.tagUpperCase;
    }

    public void setTagUpperCase(boolean tagUpperCase) {
        data.tagUpperCase = tagUpperCase;
        invalidate();
        requestLayout();
    }

    public float getTagBorderRadius() {
        return data.tagBorderRadius;
    }

    public void setTagBorderRadius(int tagBorderRadius) {
        data.tagBorderRadius = tagBorderRadius;
        invalidate();
        requestLayout();
    }

    public float getTagCircleRadius() {
        return  data.tagCircleRadius;
    }

    public void setTagCircleRadius(float tagCircleRadius) {
        data.tagCircleRadius = tagCircleRadius;
        invalidate();
        requestLayout();
    }

    public int getTagCircleColor() {
        return data.tagCircleColor;
    }

    public void setTagCircleColor(int tagCircleColor) {
        data.tagCircleColor = tagCircleColor;
        init();
        invalidate();
        requestLayout();
    }

    public int getTagTextColor() {
        return data.tagTextColor;
    }

    public void setTagTextColor(int tagTextColor) {
        data.tagTextColor = tagTextColor;
        invalidate();
        requestLayout();
    }
    
    public int getTagLeftPadding() {
        return data.tagLeftPadding;
    }

    public void setTagLeftPadding(int tagLeftPadding) {
        data.tagLeftPadding = tagLeftPadding;
        invalidate();
        requestLayout();
    }

    public int getTagRightPadding() {
        return data.tagRightPadding;
    }

    public void setTagRightPadding(int tagRightPadding) {
        data.tagRightPadding = tagRightPadding;
        invalidate();
        requestLayout();
    }

    public int getTagTopPadding() {
        return data.tagTopPadding;
    }

    public void setTagTopPadding(int tagTopPadding) {
        data.tagTopPadding = tagTopPadding;
        invalidate();
        requestLayout();
    }

    public int getTagBottomPadding() {
        return data.tagBottomPadding;
    }

    public void setTagBottomPadding(int tagBottomPadding) {
        data.tagBottomPadding = tagBottomPadding;
        invalidate();
        requestLayout();
    }
}
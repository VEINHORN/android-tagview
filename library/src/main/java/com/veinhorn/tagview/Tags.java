package com.veinhorn.tagview;

import android.graphics.Color;

/**
 * Created by veinhorn on 11.3.16.
 */
public class Tags {
    // Tag types
    public static final int CLASSIC = 0;
    public static final int MODERN = 1;
    public static final int SHARP = 2;
    public static final int MODERN_SHARP = 3;
    public static final int REVERSED_MODERN = 4;
    public static final int REVERSED_SHARP = 5;
    public static final int REVERSED_MODERN_SHARP = 6;
    // Default tag paddings
    public static final int LEFT_PADDING_DEFAULT = 15;
    public static final int RIGHT_PADDING_DEFAULT = 15;
    public static final int TOP_PADDING_DEFAULT = 10;
    public static final int BOTTOM_PADDING_DEFAULT = 10;
    // Default tag colors
    public static final int TEXT_COLOR_DEFAULT = Color.WHITE;
    public static final int CIRCLE_COLOR_DEFAULT = Color.WHITE;
    // Tag constants
    public static final int BORDER_RADIUS_DEFAULT = 5;
    public static final int CIRCLE_RADIUS_DEFAULT = 7;

    public static final int MODERN_TAG_MULTIPLIER = 2; // used to draw crop inside modern tag
    public static final int SHARP_TAG_MULTIPLIER = 3; // used to draw triangle for "sharp" tag
}

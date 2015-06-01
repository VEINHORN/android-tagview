package com.veinhorn.tagviewexample;

import android.app.Activity;
import android.os.Bundle;

import com.veinhorn.tagview.TagView;


public class MainActivity extends Activity {
    private TagView tagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tagView = (TagView)findViewById(R.id.tagView);
        tagView.setTagType(TagView.MODERN_TRAPEZIUM);
    }
}
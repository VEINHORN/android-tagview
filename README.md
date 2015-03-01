# Android TagView
Android library for creating different tags for your content.

#Usage
```xml
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    tools:context=".MainActivity">
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true">
        <com.veinhorn.tagview.TagView
            android:id="@+id/tagView"
            android:layout_margin="5px"
            android:text="Movies"
            android:textSize="20sp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_centerHorizontal="true"
            app:tagType="modern"
            app:tagColor="#ffaa66cc"
            app:tagUpperCase="true"/>
        <com.veinhorn.tagview.TagView
            android:id="@+id/tagView2"
            android:text="Images"
            android:layout_margin="5px"
            android:textSize="20sp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_centerHorizontal="true"
            app:tagType="modern"
            app:tagColor="#ff669900"
            app:tagUpperCase="true"/>
        <com.veinhorn.tagview.TagView
            android:id="@+id/tagView3"
            android:text="Music"
            android:layout_margin="5px"
            android:textSize="20sp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_centerHorizontal="true"
            app:tagType="modern"
            app:tagColor="#ffffbb33"
            app:tagUpperCase="true"/>
    </LinearLayout>
</RelativeLayout>
```

#Screenshots
![ScreenShot](http://i.imgur.com/Kiu5jsI.png?1)
![ScreenShot](http://i.imgur.com/mDMY9NO.png?2)
![ScreenShot](http://i.imgur.com/cFIfF4d.png?1)
![ScreenShot](http://i.imgur.com/qfTtlWv.png?1)
![ScreenShot](http://i.imgur.com/V9cl2Ao.png?1)
![ScreenShot](http://i.imgur.com/WUttZQv.png?1)

Customization
---------------
TagView extends TextView, so you can use all TextView methods and xml properties

* ```tagType``` - selecting type of tag(classic, modern, trapezium, modern_trapezium, modern_reversed, etc.).
* ```tagColor``` - background color of tag.
* ```tagUpperCase``` - making every tag uppercase.
* ```tagBorderRadius``` - setting border radius of tag
* ```tagCircleRadius``` - radius of crop that is inside of tag
* ```tagCircleColor``` - color of tag circle that is inside of tag
* ```tagTextColor``` - color of text

#License
===============
        Copyright 2015 Boris Korogvich
        
        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at
        
        http://www.apache.org/licenses/LICENSE-2.0
        
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.

![tag-horizontal.png](https://cdn.steemitimages.com/DQmfXQCzLmnWhMavs1XJvT5tKwAthwZdkuPXrxUGVNSVcnr/tag-horizontal.png)
# Android TagView

Android library for creating different tags for your content. Library uses TextView as a parent class. Example usages can be found in app module (folder).

## Install

```gradle
compile 'com.veinhorn.tagview:library:1.0.4'
```

## Usage

Add to your layout:

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
            app:tagType="modern"
            app:tagColor="#ffaa66cc"
            app:tagUpperCase="true"/>
        <com.veinhorn.tagview.TagView
            android:id="@+id/tagView2"
            android:text="Images"
            android:textSize="20sp"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:tagType="classic"
            app:tagColor="#ff669900"
            app:tagUpperCase="true"/>
    </LinearLayout>
</RelativeLayout>
```

## Customization
---------------
TagView extends TextView, so you can use all TextView methods and xml properties

* ```tagType``` - selecting type of tag(classic, modern, trapezium, modern_trapezium, modern_reversed, etc.).
* ```tagColor``` - background color of tag.
* ```tagUpperCase``` - making every tag uppercase.
* ```tagBorderRadius``` - setting border radius of tag
* ```tagCircleRadius``` - radius of crop that is inside of tag
* ```tagCircleColor``` - color of tag circle that is inside of tag
* ```tagTextColor``` - color of text

### Padding

The default padding values for TagView is:
* left - 15dp
* right - 15dp
* top - 10dp
* bottom - 10dp

But you can set up your own padding using getters and setters.

### Tag types

* TagView.CLASSIC
* TagView.MODERN
* TagView.TRAPEZIUM
* TagView.MODERN_TRAPEZIUM
* TagView.MODERN_REVERSED
* TagView.TRAPEZIUM_REVERSED
* TagView.MODERN_TRAPEZIUM_REVERSED

## Screenshots

![ScreenShot](http://i.imgur.com/Kiu5jsI.png?2)
![ScreenShot](http://i.imgur.com/mDMY9NO.png?3)
![ScreenShot](http://i.imgur.com/cFIfF4d.png?2)
![ScreenShot](http://i.imgur.com/qfTtlWv.png?2)
![ScreenShot](http://i.imgur.com/V9cl2Ao.png?2)
![ScreenShot](http://i.imgur.com/WUttZQv.png?2)

## License

        Copyright 2019 Boris Korogvich
        
        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at
        
        http://www.apache.org/licenses/LICENSE-2.0
        
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.

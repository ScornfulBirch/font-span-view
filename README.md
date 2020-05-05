# font-span-view
[![API](https://img.shields.io/badge/API-15%2B-orange.svg)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-Apache%202-red.svg)](https://www.apache.org/licenses/LICENSE-2.0)
![Android CI](https://github.com/lndmflngs/font-span-view/workflows/Android%20CI/badge.svg)
[![](https://jitpack.io/v/lndmflngs/font-span-view.svg)](https://jitpack.io/#lndmflngs/font-span-view)

Multi span TextView with custom fonts

<img src="https://github.com/lndmflngs/font-span-view/blob/master/art/1.png?raw=true" width="45%" />

## Download
Download the [latest release][1] or grab via Gradle:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
        implementation 'com.github.lndmflngs:font-span-view:1.0.0'
}
```
## Usage
The simplest way is to use `FontSpanView` like a normal `TextView`

```xml
        <com.lockwood.multispan.font.FontTwoSpanView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:firstFont="roboto_medium.ttf"
            app:secondFont="roboto.ttf"
            app:firstText="Username"
            app:firstSeparator=":"
            app:orientation="vertical"
            app:secondText="Mr. Fluffypants" />
```
#### Styling
You can override setSpanOnResult and set custom spans on SpannableString[]

For more information see `app` directory

## Issue Tracking
Found a bug? Have an idea for an improvement? Feel free to [add an issue](../../issues)

## License

```
Copyright (C) 2020 Ivan Zinovyev (https://github.com/lndmflngs)
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
[1]: https://github.com/lndmflngs/font-span-view/releases/latest
[2]: https://github.com/lndmflngs/font-span-view/blob/master/app/src/main/java/com/lockwood/fontspandemo/view/HighlightTextView.kt

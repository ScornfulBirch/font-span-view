package com.lockwood.multispan.font.extensions

import android.content.res.TypedArray

internal fun TypedArray.getStringOrEmpty(
    index: Int
): String {
    return getString(index) ?: ""
}
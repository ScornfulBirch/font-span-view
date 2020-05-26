package com.lockwood.fontspandemo.extensions

import android.content.res.TypedArray
import androidx.annotation.ColorInt

internal fun TypedArray.getStringOrEmpty(
    index: Int
): String {
    return getString(index) ?: ""
}

internal fun TypedArray.getTextSizeOrDefault(
    index: Int,
    default: Int
): Int {
    return getDimensionPixelSize(index, default)
}

internal fun TypedArray.getTextColorOrDefault(
    index: Int,
    @ColorInt default: Int
): Int {
    return getColor(index, default)
}
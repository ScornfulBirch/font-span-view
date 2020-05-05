/*
 * Copyright (C) 2020  Ivan Zinovyev (https://github.com/lndmflngs)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lockwood.multispan.font.span

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

class FontSpan(
    private val newType: Typeface,
    private val textSize: Float? = null,
    private val textColor: Int? = null
) : TypefaceSpan("") {

    override fun updateDrawState(ds: TextPaint) {
        ds.applyCustomTypeFace(newType)
        textSize?.let { ds.textSize = it }
        textColor?.let { ds.color = it }
    }

    override fun updateMeasureState(paint: TextPaint) {
        paint.applyCustomTypeFace(newType)
        textSize?.let { paint.textSize = it }
        textColor?.let { paint.color = it }
    }

    private fun Paint.applyCustomTypeFace(tf: Typeface) {
        val oldStyle: Int
        val old = typeface
        oldStyle = old?.style ?: 0

        val fake = oldStyle and tf.style.inv()
        if (fake and Typeface.BOLD != 0) {
            isFakeBoldText = true
        }

        if (fake and Typeface.ITALIC != 0) {
            textSkewX = -0.25f
        }

        typeface = tf
    }
}
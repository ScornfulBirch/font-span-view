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

package com.lockwood.multispan.font.base

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import com.lockwood.multispan.MultiSpanView
import com.lockwood.multispan.extensions.getStringOrEmpty
import com.lockwood.multispan.font.delegate.SpanFontDelegate
import com.lockwood.multispan.font.item.FontSpanItem
import com.lockwood.multispan.font.spannable.FontSpannable

abstract class FontMultiSpanView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : MultiSpanView<FontSpanItem>(context, attrs, defStyleAttr), FontSpannable {

    override fun initSpan(): FontSpanItem {
        return FontSpanItem(
            text = "",
            textSize = textSize.toInt(),
            textColor = currentTextColor,
            font = typeface,
            separator = ""
        )
    }

    protected open fun fetchFontFromAssets(path: String): Typeface = try {
        Typeface.createFromAsset(context.assets, path)
    } catch (e: Exception) {
        Log.e(TAG, "fetchFontFromAssets: ${e.cause}")
        typeface
    }

    //region TypedArray extensions
    protected fun TypedArray.getFontOrDefault(
        index: Int
    ): Typeface {
        val font = getStringOrEmpty(index)
        return if (font.isNotEmpty()) {
            fetchFontFromAssets(font)
        } else {
            typeface
        }
    }
    // endregion

    //region Font Span property functions
    protected inline fun fontProperty(position: () -> Int): SpanFontDelegate {
        val item = findSpan(position)
        return SpanFontDelegate(item) { updateSpanStyles() }
    }
    // endregion

    companion object {

        private val TAG = FontMultiSpanView::class.java.simpleName
    }

}


package com.lockwood.fontspandemo.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.util.Log
import com.lockwood.fontspandemo.R
import com.lockwood.fontspandemo.extensions.fetchAttrs
import com.lockwood.fontspandemo.extensions.getStringOrEmpty
import com.lockwood.fontspandemo.extensions.getTextColorOrDefault
import com.lockwood.multispan.font.FontThreeSpanView
import com.lockwood.multispan.font.span.FontSpan
import java.util.regex.PatternSyntaxException

@SuppressLint("Recycle")
class HighlightTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FontThreeSpanView(context, attrs) {

    companion object {

        private const val TAG = "HighlightTextView"

        private const val DEFAULT_IS_HIGHLIGHT = true
        private const val DEFAULT_IS_USE_UNDERLINE = false
    }

    private var isHighlight: Boolean = DEFAULT_IS_HIGHLIGHT
    private var useUnderline: Boolean = DEFAULT_IS_USE_UNDERLINE
    private var highlightFont: Typeface = typeface
    private var highlightSpanColor: Int = currentTextColor
    private var highlightPattern: String? = null
        set(value) {
            field = value
            if (field != null) {
                updateSpanStyles()
            }
        }

    init {
        fetchAttrs(context, R.styleable.HighlightTextView, attrs) {
            highlightPattern = getStringOrEmpty(R.styleable.HighlightTextView_highlightPattern)
            highlightFont = getFontOrDefault(R.styleable.HighlightTextView_highlightFont)
            highlightSpanColor = getTextColorOrDefault(
                R.styleable.HighlightTextView_highlightColor,
                defaultTextColor
            )
            isHighlight = getBoolean(
                R.styleable.HighlightTextView_isHighlight,
                DEFAULT_IS_HIGHLIGHT
            )
            useUnderline = getBoolean(
                R.styleable.HighlightTextView_useUnderline,
                DEFAULT_IS_USE_UNDERLINE
            )
        }

        updateSpanStyles()
    }

    override fun setSpanOnResult(resultSpans: SpannableString): CharSequence {
        return if (isHighlight && !highlightPattern.isNullOrEmpty()) {

            try {
                val regexPattern = highlightPattern!!.toRegex()
                val highlightMatches = regexPattern.findAll(resultSpans).map { it.value }

                highlightMatches.forEach { item ->
                    val start = resultSpans.indexOf(item)
                    val end = start + item.length

                    resultSpans.setSpan(
                        FontSpan(highlightFont, textSize, highlightSpanColor),
                        start,
                        end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    if (useUnderline) {
                        resultSpans.setSpan(
                            UnderlineSpan(),
                            start,
                            end,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
                resultSpans

            } catch (e: PatternSyntaxException) {
                Log.e(TAG, e.message.toString())
                super.setSpanOnResult(resultSpans)
            }
        } else {
            super.setSpanOnResult(resultSpans)
        }
    }

}
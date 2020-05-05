package com.lockwood.multispan.font.delegate

import android.graphics.Typeface
import com.lockwood.multispan.delegate.base.SpanDelegate
import com.lockwood.multispan.font.item.FontSpanItem
import com.lockwood.multispan.item.SpanItem
import kotlin.reflect.KProperty

class SpanFontDelegate(
    spanItem: SpanItem,
    override val onSet: () -> Unit
) : SpanDelegate<Typeface>(spanItem) {

    override fun getValue(thisRef: Any, property: KProperty<*>): Typeface {
        return (spanItem as FontSpanItem).font
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Typeface) {
        (spanItem as FontSpanItem).font = value
        onSet()
    }

}
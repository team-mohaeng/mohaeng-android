package org.journey.android.presentation.customview

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import org.journey.android.R

class SpannableMohaengTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle:Int = 0 ): AppCompatTextView(context, attrs, defStyle) {
    private var spannableStart = -1
    private var spannableEnd = -1
    private var spanColor = -1

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SpannableText,
            0,
            0
        )
        val spannableString = SpannableString(text)

        if(typedArray.hasValue(R.styleable.SpannableText_span_start) && typedArray.hasValue(R.styleable.SpannableText_span_end)) {
            spannableStart = typedArray.getInt(R.styleable.SpannableText_span_start, 0)
            spannableEnd = typedArray.getInt(R.styleable.SpannableText_span_end, 0) + 1
            spanColor = typedArray.getInt(R.styleable.SpannableText_span_color, R.color.moheang_yellow6)
            spannableString.setSpan(
                ForegroundColorSpan(spanColor), spannableStart, spannableEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            text = spannableString
        }
    }
}
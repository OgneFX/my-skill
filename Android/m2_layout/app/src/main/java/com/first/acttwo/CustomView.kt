package com.first.acttwo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.first.acttwo.databinding.MyCustomViewBinding


class CustomView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val bining = MyCustomViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(bining.root)
    }

    fun setTexttop(text: String) {
        bining.customButtonVerh.text = text
    }
    fun setTextbottom(text: String) {
        bining.customButtonNiz.text = text
    }

}



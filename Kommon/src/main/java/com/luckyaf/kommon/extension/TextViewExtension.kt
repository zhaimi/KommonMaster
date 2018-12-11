package com.luckyaf.kommon.extension

import androidx.core.content.ContextCompat
import android.widget.TextView

/**
 * 类描述：
 * @author Created by luckyAF on 2018/12/3
 *
 */
fun TextView.setColor(resId: Int) :TextView{
    this.setTextColor(ContextCompat.getColor(context,resId))
    return this
}
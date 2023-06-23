package com.core.common.ui.util

import java.text.NumberFormat
import java.util.Locale

fun String?.asPrice(): String {
    val formatter = NumberFormat.getInstance(Locale("en"))
    return if (!isNullOrEmpty()) "${formatter.format(toFloat().times(10))} ﷼"
    else "رایگان"
}
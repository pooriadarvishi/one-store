package com.core.common.ui.util

fun String?.asRating(): Float = if (!isNullOrEmpty()) toFloat()
else 0.0f
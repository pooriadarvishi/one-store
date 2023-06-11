package com.core.common.enums.common

import com.core.common.enums.common.UiText.EXPENSIVE
import com.core.common.enums.common.UiText.HATED
import com.core.common.enums.common.UiText.HIGH_SCORE
import com.core.common.enums.common.UiText.INEXPENSIVE
import com.core.common.enums.common.UiText.LOW_SCORE
import com.core.common.enums.common.UiText.NEW
import com.core.common.enums.common.UiText.OLD
import com.core.common.enums.common.UiText.POPULAR
import com.core.common.enums.common.UiText.TITLE_A_Z
import com.core.common.enums.common.UiText.TITLE_Z_A
import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter

fun OrderByFilter.asString() = this.filterName
fun OrderFilter.asString() = this.filterName


private object UiText {
    const val NEW = "جدید ترین ها"
    const val OLD = "قدیمی ترین ها"
    const val TITLE_A_Z = "حروف الفبا (آ-ی)"
    const val TITLE_Z_A = "حروف الفبا (ی-آ)"
    const val EXPENSIVE = "گران ترین ها"
    const val INEXPENSIVE = "ارزان ترین ها"
    const val POPULAR = "محبوب ترین ها"
    const val HATED = "منفور ترین ها"
    const val HIGH_SCORE = "پر امتیاز ترین ها"
    const val LOW_SCORE = "کم امتیاز ترین ها"
}


fun OrderByFilter.ui(orderFilter: OrderFilter) = when (this) {
    OrderByFilter.ID -> {
        when (orderFilter) {
            OrderFilter.DESC -> NEW
            OrderFilter.ASC -> OLD
        }
    }

    OrderByFilter.TITLE -> {
        when (orderFilter) {
            OrderFilter.DESC -> TITLE_A_Z
            OrderFilter.ASC -> TITLE_Z_A
        }
    }

    OrderByFilter.PRICE -> {
        when (orderFilter) {
            OrderFilter.DESC -> EXPENSIVE
            OrderFilter.ASC -> INEXPENSIVE
        }
    }

    OrderByFilter.POPULARITY -> {
        when (orderFilter) {
            OrderFilter.DESC -> POPULAR
            OrderFilter.ASC -> HATED
        }
    }

    OrderByFilter.RATING -> {
        when (orderFilter) {
            OrderFilter.DESC -> HIGH_SCORE
            OrderFilter.ASC -> LOW_SCORE
        }
    }
}


fun String.network() = when (this) {
    NEW -> Pair(OrderByFilter.ID, OrderFilter.DESC)
    OLD -> Pair(OrderByFilter.ID, OrderFilter.ASC)
    TITLE_A_Z -> Pair(OrderByFilter.TITLE, OrderFilter.DESC)
    TITLE_Z_A -> Pair(OrderByFilter.TITLE, OrderFilter.ASC)
    EXPENSIVE -> Pair(OrderByFilter.PRICE, OrderFilter.DESC)
    INEXPENSIVE -> Pair(OrderByFilter.PRICE, OrderFilter.ASC)
    POPULAR -> Pair(OrderByFilter.POPULARITY, OrderFilter.DESC)
    HATED -> Pair(OrderByFilter.POPULARITY, OrderFilter.ASC)
    HIGH_SCORE -> Pair(OrderByFilter.RATING, OrderFilter.DESC)
    LOW_SCORE -> Pair(OrderByFilter.RATING, OrderFilter.ASC)
    else -> Pair(OrderByFilter.ID, OrderFilter.DESC)
}


fun String.asOrderBy() = OrderByFilter.valueOf(this)

fun String.asOrder() = OrderFilter.valueOf(this)
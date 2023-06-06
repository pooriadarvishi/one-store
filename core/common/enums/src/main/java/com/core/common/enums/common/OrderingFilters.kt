package com.core.common.enums.common

import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter

class OrderingFilters {
    fun orderByData() = OrderByFilter.DATA
    fun orderById() = OrderByFilter.ID
    fun orderByInclude() = OrderByFilter.INCLUDE
    fun orderByTitle() = OrderByFilter.TITLE
    fun orderBySlug() = OrderByFilter.SLUG
    fun orderByPrice() = OrderByFilter.PRICE
    fun orderByPopularity() = OrderByFilter.POPULARITY
    fun orderByRating() = OrderByFilter.RATING

    fun descendingOrder() = OrderFilter.DESC
    fun ascendingOrder() = OrderFilter.ASC
}
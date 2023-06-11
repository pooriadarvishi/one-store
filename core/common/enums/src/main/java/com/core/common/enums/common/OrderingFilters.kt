package com.core.common.enums.common

import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter
import javax.inject.Singleton



class OrderingFilters {
    fun orderByDefault() = OrderByFilter.ID
    fun orderById() = OrderByFilter.ID
    fun orderByTitle() = OrderByFilter.TITLE
    fun orderByPrice() = OrderByFilter.PRICE
    fun orderByPopularity() = OrderByFilter.POPULARITY
    fun orderByRating() = OrderByFilter.RATING

    fun orderDefault() = OrderFilter.DESC
    fun descendingOrder() = OrderFilter.DESC
    fun ascendingOrder() = OrderFilter.ASC
}
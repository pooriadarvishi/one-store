package com.core.common.enums.common

import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter


fun pureOrder(orderFilter: OrderFilter): String = orderFilter.filterName
fun pureOrder(orderByFilter: OrderByFilter): String = orderByFilter.filterName
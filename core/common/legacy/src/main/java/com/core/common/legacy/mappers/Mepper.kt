package com.core.common.legacy.mappers

fun interface Mapper<F, T> {
    fun map(from: F): T
}
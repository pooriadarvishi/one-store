package com.core.common.legacy.mappers


fun <T, K> List<T>?.asListOfUiModel(mapperAction: T.() -> K) =
    if (!this.isNullOrEmpty())
        Mapper<T, K> { t ->
            t.mapperAction()
        }.map(this)
    else emptyList()


fun <T, K> T?.asUiModel(mapperAction: T.() -> K): K? = this?.mapperAction()


fun <T> T?.isTrueResponse(): Boolean =
    this != null

fun <T> Collection<T?>?.isTrueResponse(): Boolean =
    !this.isNullOrEmpty()

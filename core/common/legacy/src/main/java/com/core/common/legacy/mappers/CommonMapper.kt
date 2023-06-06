package com.core.common.legacy.mappers


fun <T, K> List<T>?.asListOfUiModel(mapperAction: (T) -> K) =
    if (!this.isNullOrEmpty())
        Mapper<T, K> { t ->
            mapperAction(t)
        }.map(this)
    else emptyList()


fun <T, K> T?.asUiModel(mapperAction: T.() -> K): K? = this?.mapperAction()
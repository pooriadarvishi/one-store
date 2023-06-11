package com.feature.home.util

import com.core.common.model.models.products.ProductsItem
import com.domain.commonmain.interact_result.InteractResultState

data class ItemRes(
    val title: String,
    val products: List<ProductsItem>
) {
    companion object {
        fun createItemRes(
            title: String,
            product: List<ProductsItem>
        ) = ItemRes(title, product)
    }

}
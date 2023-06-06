package com.core.common.legacy.mappers

import com.core.common.model.models.products.Image
import com.core.common.model.models.products.ProductsItem
import com.core.network.model.products.ProductsResponseItemDto


class ProductDtoToProduct : Mapper<ProductsResponseItemDto, ProductsItem> {
    override fun map(from: ProductsResponseItemDto): ProductsItem = with(from) {
        ProductsItem(
            id, averageRating, images.asListOfUiModel { imageDto ->
                with(imageDto) {
                    Image(
                        id, name, src
                    )
                }
            }, name, price, regularPrice, salePrice
        )
    }
}

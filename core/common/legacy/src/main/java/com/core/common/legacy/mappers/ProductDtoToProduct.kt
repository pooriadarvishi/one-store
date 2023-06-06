package com.core.common.legacy.mappers

import com.core.common.model.models.products.Image
import com.core.common.model.models.products.ProductsItem
import com.core.network.model.products.ProductsResponseItemDto
import com.core.network.model.products.Image as ImageDto


class ProductDtoToProduct : Mapper<ProductsResponseItemDto, ProductsItem> {
    override fun map(from: ProductsResponseItemDto): ProductsItem = with(from) {
        ProductsItem(
            id, averageRating, images.asImagesUi(), name, price, regularPrice, salePrice
        )
    }


    private fun List<ImageDto>?.asImagesUi(): List<Image> = if (this != null) {
        Mapper<ImageDto, Image> { imageDto ->
            Image(
                imageDto.id, imageDto.name, imageDto.src
            )
        }.map(
            this
        )
    } else {
        emptyList()
    }


}


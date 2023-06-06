package com.core.common.legacy.mappers

import com.core.common.model.models.details.Category
import com.core.common.model.models.details.Dimensions
import com.core.common.model.models.details.Image
import com.core.common.model.models.details.ProductDetails
import com.core.common.model.models.details.Tag
import com.core.network.model.details.ProductDetailsDto

class DetailsDtoToDetails : Mapper<ProductDetailsDto, ProductDetails> {
    override fun map(from: ProductDetailsDto): ProductDetails = with(from) {
        ProductDetails(
            id,
            name,
            description,
            price,
            regularPrice,
            salePrice,
            averageRating,
            categories.asListOfUiModel { cateGoryDto ->
                with(cateGoryDto) {
                    Category(
                        id, name, slug
                    )
                }
            },
            images.asListOfUiModel { imageDto ->
                with(imageDto) {
                    Image(
                        id, name, src
                    )
                }

            },
            ratingCount,
            shortDescription,
            tags.asListOfUiModel { tagDto ->
                with(tagDto) {
                    Tag(
                        id, name, slug
                    )
                }
            },
            relatedIds ?: emptyList(),
            weight,
            dimensions.asUiModel {
                Dimensions(height, length, width)
            }
        )
    }
}
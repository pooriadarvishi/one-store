package com.core.common.legacy.mappers

import com.core.common.model.models.category.CategoriesItem
import com.core.common.model.models.category.Image
import com.core.network.model.category.CategoriesItemDto

class CategoriesDtoToCategories : Mapper<CategoriesItemDto, CategoriesItem> {
    override fun map(from: CategoriesItemDto): CategoriesItem = with(from) {
        CategoriesItem(id, name, image.asUiModel { Image(id, name, src) }, description, count)
    }

}
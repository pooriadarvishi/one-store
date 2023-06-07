package com.domain.commonmain.interactors

import com.core.common.model.models.category.CategoriesItem
import com.data.category.data.repository.CategoryRepository
import com.domain.commonmain.interact_result.InteractResult
import com.example.common_main.result.open
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository) :
    InteractResult<GetListCategoriesUseCase.Params, List<CategoriesItem>>() {
    data class Params(
        val page: Int
    )

    override suspend fun doWork(params: Params): Flow<List<CategoriesItem>> =
        with(params) { categoryRepository.getListCategories(page).open() }


}
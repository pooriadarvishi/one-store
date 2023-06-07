package com.feature.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.models.category.CategoriesItem
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListCategoriesUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val getListCategoriesUseCase: GetListCategoriesUseCase) :
    ViewModel() {


    init {
        getListCategories(1)
    }


    private val _categories = MutableLiveData<InteractResultState<List<CategoriesItem>>>()
    val categories: LiveData<InteractResultState<List<CategoriesItem>>> = _categories


    private fun getListCategories(page: Int) {
        viewModelScope.launch {
            val params = GetListCategoriesUseCase.Params(page)
            getListCategoriesUseCase(params).collectLatest { resultCategoriesItem ->
                _categories.postValue(resultCategoriesItem)
            }
        }
    }
}
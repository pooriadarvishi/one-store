package com.feature.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.models.category.CategoriesItem
import com.data.category.data.repository.CategoryRepository
import com.example.common_main.result.Result
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {


    init {
        getListCategories(1)
    }


    private val _categories = MutableLiveData<Result<List<CategoriesItem>>>()
    val categories: LiveData<Result<List<CategoriesItem>>> = _categories


    private fun getListCategories(page: Int) {
        viewModelScope.launch {
            categoryRepository.getListCategories(page).collectLatest { resultCategoriesItem ->
                _categories.postValue(resultCategoriesItem)
            }
        }
    }
}
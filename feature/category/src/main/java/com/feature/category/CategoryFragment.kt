package com.feature.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.common.ui.ui.BaseFragment
import com.core.common.ui.ui.BaseViewModel
import com.feature.category.databinding.FragmentCategoryBinding
import com.feature.category.util.adapters.CategoryAdapter
import com.feature.products.ProductsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment() {

    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var categoryBinding: FragmentCategoryBinding
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoryBinding = FragmentCategoryBinding.inflate(inflater)
        return categoryBinding.root
    }


    private fun setAdapter() {
        adapter = CategoryAdapter { onProductsByCategory(it) }
        categoryBinding.recyclerViewCategory.adapter = adapter

    }


    private fun setPagination() {
        categoryBinding.recyclerViewCategory.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    categoryViewModel.nextPage()
                }
            }
        })
    }

    private fun onProductsByCategory(categoryId: Int) {
        val intent = Intent(requireContext(), ProductsActivity::class.java)
        intent.putExtra(ProductsActivity.CATEGORY, categoryId)
        startActivity(intent)
    }

    private fun observeCategory() {
        observe(categoryViewModel.data) { adapter.submitList(it) }
    }

    private fun observeUiState() {
        observe(categoryViewModel.uiState) { state ->
            when (state) {
                BaseViewModel.UiState.SUCCESS -> bindSuccess()
                BaseViewModel.UiState.FAIL -> bindFail()
                BaseViewModel.UiState.LOADING -> bindLoading()
            }
        }
    }



    override fun bindLoading() {
        categoryBinding.apply {
            networkImage.isInvisible = true
            recyclerViewCategory.isInvisible = true
            progressBar.isInvisible = false
        }
    }

    override fun bindSuccess() {
        categoryBinding.apply {
            networkImage.isInvisible = true
            recyclerViewCategory.isInvisible = false
            progressBar.isInvisible = true
        }
    }

    override fun bindFail() {
        categoryBinding.apply {
            networkImage.isInvisible = false
            recyclerViewCategory.isInvisible = true
            progressBar.isInvisible = true
        }
    }


    override fun setUI() {
        setAdapter()
        observeUiState()
        observeCategory()
        setPagination()
    }


}
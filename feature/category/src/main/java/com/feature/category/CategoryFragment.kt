package com.feature.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feature.category.databinding.FragmentCategoryBinding
import com.feature.category.util.adapters.CategoryAdapter
import com.feature.products.ProductsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }


    private fun setAdapter() {
        adapter = CategoryAdapter { showDetail(it) }
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

    private fun showDetail(productId: Int) {
        val intent = Intent(requireContext(), ProductsActivity::class.java)
        intent.putExtra(ProductsActivity.CATEGORY, productId)
        startActivity(intent)
    }

    private fun observeCategory() {
        observe(categoryViewModel.categories) { adapter.submitList(it) }
    }

    private fun observeStateNetwork() {
        observe(categoryViewModel.stateNetwork) { state ->
            when (state) {
                CategoryViewModel.StateNetwork.SUCCESS -> bindSuccess()
                CategoryViewModel.StateNetwork.FAIL -> bindFail()
                CategoryViewModel.StateNetwork.LOADING -> bindLoading()
            }
        }
    }

    private fun <T> observe(flow: StateFlow<T>, action: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect {
                    action(it)
                }
            }
        }
    }


    private fun bindLoading() {
        categoryBinding.apply {
            networkImage.isInvisible = true
            recyclerViewCategory.isInvisible = true
            progressBar.isInvisible = false
        }
    }

    private fun bindSuccess() {
        categoryBinding.apply {
            networkImage.isInvisible = true
            recyclerViewCategory.isInvisible = false
            progressBar.isInvisible = true
        }
    }

    private fun bindFail() {
        categoryBinding.apply {
            networkImage.isInvisible = false
            recyclerViewCategory.isInvisible = true
            progressBar.isInvisible = true
        }
    }


    private fun setUI() {
        setAdapter()
        observeStateNetwork()
        observeCategory()
        setPagination()
    }


}
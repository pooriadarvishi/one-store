package com.feature.products.ui.products

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.common.ui.ui.BaseFragment
import com.core.common.ui.ui.BaseViewModel
import com.feature.details.DetailsActivity
import com.feature.products.ProductsActivity.Companion.CATEGORY
import com.feature.products.ProductsActivity.Companion.ORDER
import com.feature.products.databinding.FragmentProductsBinding
import com.feature.products.util.adapters.ProductsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductsFragment : BaseFragment() {

    private lateinit var binding: FragmentProductsBinding
    private val productsViewModel: ProductsViewModel by viewModels()
    private lateinit var adapter: ProductsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater)
        return binding.root
    }

    private fun start() {
        val intent = requireActivity().intent
        if (intent.hasExtra(CATEGORY)) {
            productsViewModel.setCategoryId(intent.getIntExtra(CATEGORY, 0))
        } else if (intent.hasExtra(ORDER)) {
            productsViewModel.setOrder(
                intent.getStringExtra(ORDER) ?: productsViewModel.getOrderDDefault()
            )
        }
        productsViewModel()
    }

    private fun setAdapter() {
        adapter = ProductsListAdapter { showDetails(it) }
        binding.recyclerView.adapter = adapter
    }

    private fun observeData() {
        observe(productsViewModel.data) {
            adapter.submitList(it)
        }
    }

    private fun observeUiState() {
        observe(productsViewModel.uiState) { state ->
            when (state) {
                BaseViewModel.UiState.SUCCESS -> bindSuccess()
                BaseViewModel.UiState.FAIL -> bindFail()
                BaseViewModel.UiState.LOADING -> bindLoading()
            }
        }
    }

    private fun showDetails(productId: Int) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.PRODUCT_ID, productId)
        startActivity(intent)
    }


    override fun bindLoading() {
        binding.apply {
            networkImage.isInvisible = true
            recyclerView.isInvisible = true
            progressBar.isInvisible = false
        }
    }

    override fun bindSuccess() {
        binding.apply {
            networkImage.isInvisible = true
            recyclerView.isInvisible = false
            progressBar.isInvisible = true
        }
    }

    override fun bindFail() {
        binding.apply {
            networkImage.isInvisible = false
            recyclerView.isInvisible = true
            progressBar.isInvisible = true
        }
    }

    private fun setPagination() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    productsViewModel.nextPage()
                }
            }
        })
    }

    override fun setUI() {
        start()
        setAdapter()
        observeUiState()
        observeData()
        setPagination()
    }
}
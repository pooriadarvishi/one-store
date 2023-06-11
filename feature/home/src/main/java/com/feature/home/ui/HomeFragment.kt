package com.feature.home.ui

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
import com.core.common.ui.ui.BaseFragment
import com.core.common.ui.ui.BaseViewModel
import com.feature.details.DetailsActivity
import com.feature.home.databinding.FragmentHomeBinding
import com.feature.home.util.adapters.MainProductAdapter
import com.feature.products.ProductsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MainProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    private fun showMore(orderByFilter: String) {
        val intent = Intent(requireContext(), ProductsActivity::class.java)
        intent.putExtra(ProductsActivity.ORDER, orderByFilter)
        startActivity(intent)
    }

    private fun showDetails(productId: Int) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.PRODUCT_ID, productId)
        startActivity(intent)
    }

    private fun setAdapter() {
        adapter = MainProductAdapter({ showDetails(it) }) { showMore(it) }
        binding.homeRecycler.adapter = adapter
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.data.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun observeUiState() {
        observe(homeViewModel.uiState) { state ->
            when (state) {
                BaseViewModel.UiState.SUCCESS -> bindSuccess()
                BaseViewModel.UiState.FAIL -> bindFail()
                BaseViewModel.UiState.LOADING -> bindLoading()
            }
        }
    }


    override fun setUI() {
        setAdapter()
        observe()
        observeUiState()
    }

    override fun bindFail() {
        binding.hostError.isInvisible = false
        binding.progressBar.isInvisible = true
        binding.homeRecycler.isInvisible = true
    }

    override fun bindSuccess() {
        binding.hostError.isInvisible = true
        binding.progressBar.isInvisible = true
        binding.homeRecycler.isInvisible = false
    }

    override fun bindLoading() {
        binding.homeRecycler.isInvisible = true
        binding.hostError.isInvisible = true
        binding.progressBar.isInvisible = false
    }
}


package com.feature.details.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.model.models.details.ProductDetails
import com.core.common.ui.ui.BaseFragment
import com.core.common.ui.util.asPrice
import com.core.common.ui.util.asRating
import com.domain.commonmain.interact_result.InteractResultState
import com.feature.details.DetailsActivity.Companion.PRODUCT_ID
import com.feature.details.databinding.FragmentDetailsBinding
import com.feature.details.util.adapters.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {
    private val detailsViewModel: DetailsViewModel by viewModels()
    private lateinit var detailsBinding: FragmentDetailsBinding
    private lateinit var adapter: ImageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentDetailsBinding.inflate(inflater)
        return detailsBinding.root
    }


    private fun start() {
        val intent = requireActivity().intent
        if (intent.hasExtra(PRODUCT_ID)) {
            detailsViewModel.setProductId(intent.getIntExtra(PRODUCT_ID, 0))
        }
    }

    private fun observe() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.data.collect { resultState ->
                    when (resultState) {
                        InteractResultState.Error -> bindFail()
                        InteractResultState.Loading -> bindLoading()
                        is InteractResultState.Success -> {
                            bindSuccess()
                            bind(resultState.data)
                        }
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = ImageAdapter()
        detailsBinding.rvImages.adapter = adapter
    }

    override fun bindLoading() {
//        detailsBinding.apply {
//            hostError.isInvisible = true
//            hostDetails.isInvisible = true
//            progressBar.isInvisible = false
//        }
    }

    override fun bindSuccess() {
//        detailsBinding.apply {
//            hostError.isInvisible = true
//            hostDetails.isInvisible = false
//            progressBar.isInvisible = true
//        }
    }

    override fun bindFail() {
//        detailsBinding.apply {
//            hostError.isInvisible = false
//            hostDetails.isInvisible = true
//            progressBar.isInvisible = true
//        }
    }


    private fun bind(productsItem: ProductDetails) {
        adapter.submitList(productsItem.images)
        detailsBinding.apply {
            tvProductName.text = productsItem.name
            tvProductPrice.text = productsItem.price.asPrice()
            tvDescription.text = productsItem.description
            ratingBar.rating = productsItem.averageRating.asRating()
        }
    }

    override fun setUI() {
        start()
        setAdapter()
        observe()

    }

}
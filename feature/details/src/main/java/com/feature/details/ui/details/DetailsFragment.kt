package com.feature.details.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.core.common.model.models.details.ProductDetails
import com.domain.commonmain.interact_result.InteractResultState
import com.feature.details.DetailsActivity.Companion.PRODUCT_ID
import com.feature.details.databinding.FragmentDetailsBinding
import com.feature.details.util.adapters.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }


    private fun start() {
        val intent = requireActivity().intent
        if (intent.hasExtra(PRODUCT_ID)) {
            detailsViewModel.setProductId(intent.getIntExtra(PRODUCT_ID, 0))
            Log.e("POOOORIAS", "start: ${intent.getIntExtra(PRODUCT_ID, 0)}")
        }
        detailsViewModel()
    }

    private fun observe() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.productDetails.collect { resultState ->
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
        detailsBinding.recyclerViewGallery.adapter = adapter
    }

    private fun bindLoading() {
        detailsBinding.apply {
            imageView.isInvisible = true
            hostDetails.isInvisible = true
            progressBar.isInvisible = false
        }
    }

    private fun bindSuccess() {
        detailsBinding.apply {
            imageView.isInvisible = true
            hostDetails.isInvisible = false
            progressBar.isInvisible = true
        }
    }

    private fun bindFail() {
        detailsBinding.apply {
            imageView.isInvisible = false
            hostDetails.isInvisible = true
            progressBar.isInvisible = true
        }
    }


    private fun bind(productsItem: ProductDetails) {
        adapter.submitList(productsItem.images)
        detailsBinding.apply {
            tvTitle.text = productsItem.name
            tvPrice.text = "${ productsItem.price }تومان"
            tvDescription.text = productsItem.description
            ratingBar.rating = productsItem.averageRating?.toFloat() ?: 0.0f
        }
    }

    private fun setUi() {
        start()
        setAdapter()
        observe()

    }

}
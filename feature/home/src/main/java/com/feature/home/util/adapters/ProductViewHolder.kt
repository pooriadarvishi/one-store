package com.feature.home.util.adapters

import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.products.ProductsItem
import com.core.common.ui.util.asPrice
import com.core.common.ui.util.imageLoading.loadImage
import com.feature.home.R
import com.feature.home.databinding.HomeItemBinding

class ProductViewHolder(private val binding: HomeItemBinding, private val showDetails: clickDet) :
    RecyclerView.ViewHolder(binding.root) {


    private lateinit var item: ProductsItem
    fun bind(productItem: ProductsItem) {
        item = productItem

        binding.root.setOnClickListener {
            item.id?.let { showDetails(it) }
        }
        binding.apply {
            tvPrice.text = item.price.asPrice()
            tvAdapter.text = item.name
            imageViewAdapter.setImageResource(R.drawable.ic_launcher_background)
            root.loadImage(imageViewAdapter, item.images?.first()?.src)
        }
    }
}
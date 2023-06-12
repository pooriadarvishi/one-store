package com.feature.products.util.adapters

import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.products.ProductsItem
import com.core.common.ui.util.asPrice
import com.core.common.ui.util.imageLoading.loadImage
import com.feature.products.databinding.ProductItemBinding

class ProductViewHolder(private val binding: ProductItemBinding,private val click: clickDet) :
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var item: ProductsItem

    init {
        binding.root.setOnClickListener { item.id?.let { click(it) } }
    }

    fun bind(productItem: ProductsItem) {
        item = productItem
        binding.apply {
            tvPrice.text = item.price.asPrice()
            tvAdapter.text = item.name
            root.loadImage(imageViewAdapter, item.images?.first()?.src)
        }
    }
}

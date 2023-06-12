package com.feature.home.util.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.core.common.model.models.products.ProductsItem
import com.feature.home.databinding.HomeItemBinding

class ProductAdapter(private val showDetails: clickDet) :
    ListAdapter<ProductsItem, ProductViewHolder>(object :
        DiffUtil.ItemCallback<ProductsItem>() {
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
            oldItem == newItem
    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            showDetails
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
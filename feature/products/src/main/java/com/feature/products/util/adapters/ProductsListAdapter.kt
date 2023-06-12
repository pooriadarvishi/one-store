package com.feature.products.util.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.core.common.model.models.products.ProductsItem
import com.feature.products.databinding.ProductItemBinding

typealias clickDet = (Int) -> Unit

class ProductsListAdapter(private val click: clickDet) :
    ListAdapter<ProductsItem, ProductViewHolder>(object :
        DiffUtil.ItemCallback<ProductsItem>() {
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
            newItem.id == oldItem.id


        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
            newItem == oldItem

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), click
        )


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
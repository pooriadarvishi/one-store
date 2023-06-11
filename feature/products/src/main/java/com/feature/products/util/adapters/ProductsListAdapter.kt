package com.feature.products.util.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.products.ProductsItem
import com.example.common_main.imageLoading.loadImage
import com.feature.products.databinding.ProductItemBinding

typealias clickDet = (Int) -> Unit

class ProductsListAdapter(private val click: clickDet) :
    ListAdapter<ProductsItem, ProductsListAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<ProductsItem>() {
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
            newItem.id == oldItem.id


        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
            newItem == oldItem

    }) {
    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: ProductsItem

        init {
            binding.root.setOnClickListener { item.id?.let { click(it) } }
        }

        fun bind(productItem: ProductsItem) {
            item = productItem
            binding.apply {
                tvPrice.text = "${item.price}تومان"
                tvAdapter.text = item.name
                ratingBar.rating = item.averageRating?.toFloat() ?: 0.0F
                root.loadImage(imageViewAdapter, item.images?.first()?.src)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
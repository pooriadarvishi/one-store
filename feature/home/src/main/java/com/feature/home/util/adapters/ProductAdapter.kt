package com.feature.home.util.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.products.ProductsItem
import com.example.common_main.imageLoading.loadImage
import com.feature.home.R
import com.feature.home.databinding.HomeItemBinding

class ProductAdapter : ListAdapter<ProductsItem, ProductAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<ProductsItem>() {
    override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean =
        oldItem == newItem
}) {
    class ViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        private lateinit var item: ProductsItem
        fun bind(productItem: ProductsItem) {
            item = productItem
            binding.apply {
                binding.tvPrice.text = "${item.price}تومان"
                binding.tvAdapter.text = item.name
                binding.ratingBar.rating = item.averageRating?.toFloat() ?: 0.0F
                binding.imageViewAdapter.setImageResource(R.drawable.ic_launcher_background)
                root.loadImage(imageViewAdapter, item.images?.first()?.src)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
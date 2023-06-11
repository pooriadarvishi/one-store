package com.feature.category.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.category.CategoriesItem
import com.example.common_main.imageLoading.loadImage
import com.feature.category.databinding.CategoryItemBinding

typealias click = (Int) -> Unit

class CategoryAdapter(private val clickCategory: click) :
    ListAdapter<CategoriesItem, CategoryAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<CategoriesItem>() {
        override fun areItemsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean =
            oldItem == newItem

    }) {


    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var categoryItem: CategoriesItem

        init {
            binding.root.setOnClickListener {
                clickCategory(categoryItem.id)
            }
        }

        fun bind(item: CategoriesItem) {
            categoryItem = item
            binding.apply {
                tvCategory.text = categoryItem.name
                root.loadImage(imageViewCategory, categoryItem.image?.src)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
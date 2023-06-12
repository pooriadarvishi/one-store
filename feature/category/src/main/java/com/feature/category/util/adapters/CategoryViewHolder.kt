package com.feature.category.util.adapters

import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.category.CategoriesItem
import com.core.common.ui.util.imageLoading.loadImage
import com.feature.category.databinding.CategoryItemBinding

class CategoryViewHolder(
    private val binding: CategoryItemBinding,
    private val clickCategory: click
) :
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
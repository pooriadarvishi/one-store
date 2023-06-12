package com.feature.category.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.category.CategoriesItem
import com.core.common.ui.util.imageLoading.loadImage
import com.feature.category.databinding.CategoryItemBinding

typealias click = (Int) -> Unit

class CategoryAdapter(private val clickCategory: click) :
    ListAdapter<CategoriesItem, CategoryViewHolder>(object :
        DiffUtil.ItemCallback<CategoriesItem>() {
        override fun areItemsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CategoriesItem, newItem: CategoriesItem): Boolean =
            oldItem == newItem

    }) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickCategory
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
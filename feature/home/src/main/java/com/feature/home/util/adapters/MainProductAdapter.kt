package com.feature.home.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.HomeMainItemBinding
import com.feature.home.util.ItemRes

typealias click = (String) -> Unit
typealias clickDet = (Int) -> Unit

class MainProductAdapter(private val showDetails: clickDet, private val showMore: click) :
    ListAdapter<ItemRes, MainProductViewHolder>(object : DiffUtil.ItemCallback<ItemRes>() {
        override fun areItemsTheSame(oldItem: ItemRes, newItem: ItemRes): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ItemRes, newItem: ItemRes): Boolean =
            oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProductViewHolder = MainProductViewHolder(
        HomeMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        showMore,
        showDetails
    )

    override fun onBindViewHolder(holder: MainProductViewHolder, position: Int) {
        val x = getItem(position)
        holder.bind(x)
    }
}
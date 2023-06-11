package com.feature.home.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.HomeMainItemBinding
import com.feature.home.util.ItemRes

typealias click = (String) -> Unit
typealias clickDet = (Int) -> Unit

class MainProductAdapter(private val showDetails: clickDet, private val showMore: click) :
    ListAdapter<ItemRes, MainProductAdapter.ViewHolder>(object : DiffUtil.ItemCallback<ItemRes>() {
        override fun areItemsTheSame(oldItem: ItemRes, newItem: ItemRes): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ItemRes, newItem: ItemRes): Boolean =
            oldItem == newItem
    }) {
    inner class ViewHolder(private val binding: HomeMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: ItemRes

        init {
            binding.tvFullShow.setOnClickListener {
                showMore(item.title)
            }
        }

        fun bind(itemRes: ItemRes) {
            item = itemRes
            val adapter = ProductAdapter(showDetails)
            binding.recyclerView.adapter = adapter
            binding.title.text = item.title
            adapter.submitList(itemRes.products)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(HomeMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val x = getItem(position)
        holder.bind(x)
    }
}
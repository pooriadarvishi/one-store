package com.feature.home.util.adapters

import androidx.recyclerview.widget.RecyclerView
import com.feature.home.databinding.HomeMainItemBinding
import com.feature.home.util.ItemRes

class MainProductViewHolder(
    private val binding: HomeMainItemBinding,
    private val showMore: click,
    private val showDetails: clickDet
) : RecyclerView.ViewHolder(binding.root) {

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

package com.feature.home.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.domain.commonmain.interact_result.InteractResultState
import com.feature.home.databinding.HomeMainItemBinding
import com.feature.home.util.ItemRes

class MainProductAdapter :
    ListAdapter<ItemRes, MainProductAdapter.ViewHolder>(object : DiffUtil.ItemCallback<ItemRes>() {
        override fun areItemsTheSame(oldItem: ItemRes, newItem: ItemRes): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: ItemRes, newItem: ItemRes): Boolean =
            oldItem == newItem
    }) {
    inner class ViewHolder(private val binding: HomeMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: ItemRes
        fun bind(itemRes: ItemRes) {
            item = itemRes
            val adapter = ProductAdapter()
            binding.recyclerView.adapter = adapter
            binding.title.text = item.title
            when (val product = item.products) {
                InteractResultState.Error -> bindError()

                InteractResultState.Loading -> bindLoading()

                is InteractResultState.Success -> {
                    bindSuccess()
                    adapter.submitList(product.data)
                }
            }
        }

        private fun bindError() {
            binding.imageView.isInvisible = false
            binding.progressBar.isInvisible = true
            binding.recyclerView.isInvisible = true
        }

        private fun bindSuccess() {
            binding.imageView.isInvisible = true
            binding.progressBar.isInvisible = true
            binding.recyclerView.isInvisible = false
        }

        private fun bindLoading() {
            binding.recyclerView.isInvisible = true
            binding.imageView.isInvisible = true
            binding.progressBar.isInvisible = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(HomeMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val x = getItem(position)
        holder.bind(x)
    }
}
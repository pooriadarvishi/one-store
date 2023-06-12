package com.feature.details.util.adapters

import androidx.recyclerview.widget.RecyclerView
import com.core.common.model.models.details.Image
import com.core.common.ui.util.imageLoading.loadImage
import com.feature.details.databinding.ImageDetailsItemBinding

class ImageViewHolder(private val binding: ImageDetailsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Image) {
        binding.root.loadImage(binding.imageProduct, item.src)
    }

}
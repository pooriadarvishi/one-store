package com.example.common_main.imageLoading

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


fun View.loadImage(image: ImageView, src: String?) {
    Glide.with(this)
        .load(src)
        .into(image)
}

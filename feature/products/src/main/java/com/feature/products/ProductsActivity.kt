package com.feature.products

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    companion object {
        const val CATEGORY = "Category"
        const val ORDER = "Order"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
    }
}
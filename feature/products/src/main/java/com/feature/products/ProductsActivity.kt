package com.feature.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.feature.products.ui.products.ProductsFragment

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance())
                .commitNow()
        }
    }
}
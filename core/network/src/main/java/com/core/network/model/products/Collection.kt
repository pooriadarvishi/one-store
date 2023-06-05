package com.core.network.model.products


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("href")
    val href: String?
)
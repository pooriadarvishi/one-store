package com.core.network.model.products


import com.google.gson.annotations.SerializedName

data class Self(
    @SerializedName("href")
    val href: String?
)
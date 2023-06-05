package com.core.network.model.category


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("collection")
    val collection: List<Collection?>?,
    @SerializedName("self")
    val self: List<Self?>?,
    @SerializedName("up")
    val up: List<Up?>?
)
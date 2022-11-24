package com.dk.newprojectsearchmovie.data.model.detailMovie


import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("imDbRating")
    val imDbRating: String
)
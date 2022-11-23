package com.dk.newprojectsearchmovie.data.model.search


import com.google.gson.annotations.SerializedName

data class SearchMovie(
    @SerializedName("contentRating")
    val contentRating: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("genres")
    val genres: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String,
    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("metacriticRating")
    val metacriticRating: String,
    @SerializedName("plot")
    val plot: String,
    @SerializedName("runtimeStr")
    val runtimeStr: String,
    @SerializedName("starList")
    val starList: List<Star>,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("title")
    val title: String
)
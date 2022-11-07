package com.dk.newprojectsearchmovie.model.imdb


import com.google.gson.annotations.SerializedName

data class ImdbMovieList(
    @SerializedName("items")
    val movies: List<ImdbMovieListItem>?
)

data class ImdbMovieListItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("imDbRating")
    val imDbRating: String?,
    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("rank")
    val rank: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("year")
    val year: String?
)
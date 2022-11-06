package com.dk.newprojectsearchmovie.model.imdb


import com.google.gson.annotations.SerializedName

data class ImdbMovieList(
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("items")
    val movies: List<ImdbMovie>?
)

data class ImdbMovie(
    @SerializedName("crew")
    val crew: String?,
    @SerializedName("fullTitle")
    val fullTitle: String?,
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
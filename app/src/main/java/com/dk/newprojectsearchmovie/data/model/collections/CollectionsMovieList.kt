package com.dk.newprojectsearchmovie.model.imdb


import com.dk.newprojectsearchmovie.data.model.Movie
import com.google.gson.annotations.SerializedName


data class CollectionsMovieList(
    @SerializedName("items")
    val movies: List<CollectionsMovie>
)

data class CollectionsMovie(
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String,
    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("crew")
    val crew: String,
    val favorite: Boolean = false
): Movie()

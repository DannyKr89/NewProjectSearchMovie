package com.dk.newprojectsearchmovie.data.model.search


import com.dk.newprojectsearchmovie.data.model.Movie
import com.google.gson.annotations.SerializedName

data class RequestSearchMovie(
    @SerializedName("results")
    val searchMovie: List<SearchMovie>
)

data class SearchMovie(
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String,
    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("stars")
    val stars: String,
    val favorite: Boolean = false,

    @SerializedName("contentRating")
    val contentRating: String,
    @SerializedName("genres")
    val genres: String,
    @SerializedName("runtimeStr")
    val runtimeStr: String
) : Movie()
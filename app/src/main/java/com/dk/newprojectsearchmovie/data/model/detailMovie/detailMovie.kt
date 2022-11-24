package com.dk.newprojectsearchmovie.data.model.detailMovie


import com.google.gson.annotations.SerializedName

data class ImdbMovieDetail(
    @SerializedName("actorList")
    val actorList: List<Actor>,
    @SerializedName("awards")
    val awards: String,
    @SerializedName("companies")
    val companies: String,
    @SerializedName("contentRating")
    val contentRating: String,
    @SerializedName("countries")
    val countries: String,
    @SerializedName("directors")
    val directors: String,
    @SerializedName("genres")
    val genres: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String?,
    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("languages")
    val languages: String,
    @SerializedName("metacriticRating")
    val metacriticRating: String,
    @SerializedName("plotLocal")
    val plotLocal: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("runtimeStr")
    val runtimeStr: String,
    @SerializedName("similars")
    val similars: List<Similar>,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("writers")
    val writers: String,
    @SerializedName("year")
    val year: String?,
    val favorite: Boolean = false
)
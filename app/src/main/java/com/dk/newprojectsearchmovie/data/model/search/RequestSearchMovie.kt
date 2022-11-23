package com.dk.newprojectsearchmovie.data.model.search


import com.google.gson.annotations.SerializedName

data class RequestSearchMovie(
    @SerializedName("errorMessage")
    val errorMessage: Any,
    @SerializedName("queryString")
    val queryString: String,
    @SerializedName("results")
    val searchMovie: List<SearchMovie>
)
package com.dk.newprojectsearchmovie.model.imdbMovie


import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("asCharacter")
    val asCharacter: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
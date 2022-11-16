package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail

interface MovieDetailCallback {
    fun onResponse(imdbMovieDetail: ImdbMovieDetail)
    fun onFailure()
}
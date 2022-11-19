package com.dk.newprojectsearchmovie.data.common

import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail

interface MovieDetailCallback {
    fun onResponse(imdbMovieDetail: ImdbMovieDetail)
}
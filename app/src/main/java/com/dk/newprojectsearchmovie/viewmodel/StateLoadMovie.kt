package com.dk.newprojectsearchmovie.viewmodel

import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail

sealed class StateLoadMovie {
        data class SuccessLoad(val imdbMovieDetail: ImdbMovieDetail) : StateLoadMovie()
        data class ErrorLoad(val imdbMovieDetail: ImdbMovieDetail) : StateLoadMovie()
        object Loading : StateLoadMovie()
}
package com.dk.newprojectsearchmovie.presentation.viewmodel

import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail

sealed class StateLoadMovie {
        data class SuccessLoad(val imdbMovieDetail: ImdbMovieDetail) : StateLoadMovie()
        data class ErrorLoad(val imdbMovieDetail: ImdbMovieDetail) : StateLoadMovie()
        object Loading : StateLoadMovie()
}
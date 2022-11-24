package com.dk.newprojectsearchmovie.presentation.view.states

import com.dk.newprojectsearchmovie.data.model.detailMovie.ImdbMovieDetail

sealed class StateLoadMovie {
        data class SuccessLoad(val imdbMovieDetail: ImdbMovieDetail) : StateLoadMovie()
        data class ErrorLoad(val throwable: Throwable) : StateLoadMovie()
        object Loading : StateLoadMovie()
}
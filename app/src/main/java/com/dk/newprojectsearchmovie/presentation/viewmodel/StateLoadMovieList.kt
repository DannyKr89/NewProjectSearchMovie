package com.dk.newprojectsearchmovie.presentation.viewmodel


sealed class StateLoadMovieList {
    data class SuccessLoad(val movieList: Any) : StateLoadMovieList()
    data class ErrorLoad(val throwable: Throwable) : StateLoadMovieList()
    object Loading : StateLoadMovieList()
}
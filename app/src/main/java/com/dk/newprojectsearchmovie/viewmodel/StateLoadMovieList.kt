package com.dk.newprojectsearchmovie.viewmodel

import com.dk.newprojectsearchmovie.model.Movie

sealed class StateLoadMovieList {
    data class SuccesLoad(val movieList: List<Movie>) : StateLoadMovieList()
    data class ErrorLoad(val throwable: Throwable) : StateLoadMovieList()
    object Loading : StateLoadMovieList()
}
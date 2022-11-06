package com.dk.newprojectsearchmovie.viewmodel

import com.dk.newprojectsearchmovie.model.Movie

sealed class StateLoadMovieList {
    data class SuccessLoad(val movieList: List<Movie>) : StateLoadMovieList()
    data class ErrorLoad(val movieList: List<Movie>) : StateLoadMovieList()
    object Loading : StateLoadMovieList()
}
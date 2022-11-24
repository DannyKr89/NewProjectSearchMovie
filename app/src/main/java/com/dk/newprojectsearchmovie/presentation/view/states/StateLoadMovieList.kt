package com.dk.newprojectsearchmovie.presentation.view.states

import com.dk.newprojectsearchmovie.data.model.Movie

sealed class StateLoadMovieList{
    data class SuccessLoad(val movieList: List<Movie>) : StateLoadMovieList()
    data class ErrorLoad(val throwable: Throwable) : StateLoadMovieList()
    object Loading : StateLoadMovieList()
}
package com.dk.newprojectsearchmovie.viewmodel

import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList

sealed class StateLoadMovieList {
    data class SuccessLoad(val movieList: ImdbMovieList) : StateLoadMovieList()
    data class ErrorLoad(val movieList: ImdbMovieList) : StateLoadMovieList()
    object Loading : StateLoadMovieList()
}
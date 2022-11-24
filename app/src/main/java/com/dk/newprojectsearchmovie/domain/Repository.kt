package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.data.model.detailMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovieList
import retrofit2.Callback

interface Repository {
    fun getMovieList(movieListType: String, callback: Callback<CollectionsMovieList>)
    fun getMovieDetail(movieID: String, callback: Callback<ImdbMovieDetail>)
    fun getMovieSearchList(
        titleQuery: String, callback: Callback<RequestSearchMovie>
    )
}

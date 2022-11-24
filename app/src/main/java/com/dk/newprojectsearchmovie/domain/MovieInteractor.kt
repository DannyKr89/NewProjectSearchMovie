package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.data.model.detailMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.data.repository.RemoteRepositoryImpl
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovieList
import retrofit2.Callback

class MovieInteractor() {

    fun getList(movieListType: String, callback: Callback<CollectionsMovieList>) {
        return RemoteRepositoryImpl().getMovieList(movieListType, callback)
    }

    fun getDetail(movieID: String, callback: Callback<ImdbMovieDetail>) {
        return RemoteRepositoryImpl().getMovieDetail(movieID, callback)
    }

    fun getSearchList(titleQuery: String, callback: Callback<RequestSearchMovie>) {
        return RemoteRepositoryImpl().getMovieSearchList(titleQuery, callback)
    }
}
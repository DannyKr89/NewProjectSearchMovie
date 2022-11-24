package com.dk.newprojectsearchmovie.data.repository

import com.dk.newprojectsearchmovie.data.RequestAPI
import com.dk.newprojectsearchmovie.data.model.detailMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.domain.Repository
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovieList
import retrofit2.Callback

class RemoteRepositoryImpl : Repository {
    private val requestAPI = RequestAPI.create()

    override fun getMovieList(movieListType: String, callback: Callback<CollectionsMovieList>) {
        return requestAPI.getMovieList(movieListType).enqueue(callback)
    }

    override fun getMovieDetail(movieID: String, callback: Callback<ImdbMovieDetail>) {
        return requestAPI.getMovieDetail(movieID).enqueue(callback)
    }

    override fun getMovieSearchList(titleQuery: String, callback: Callback<RequestSearchMovie>) {
        return requestAPI.getSearchMovieList(titleQuery).enqueue(callback)
    }
}
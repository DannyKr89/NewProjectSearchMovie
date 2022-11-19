package com.dk.newprojectsearchmovie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.common.MovieDetailCallback
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.repository.LocalRepositoryImpl
import com.dk.newprojectsearchmovie.data.repository.RemoteRepositoryImpl
import com.dk.newprojectsearchmovie.model.imdb.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(
    private var getMovieDetail: MutableLiveData<StateLoadMovie> = MutableLiveData()
): ViewModel() {

    fun getMovieDetailState(): MutableLiveData<StateLoadMovie> {
        return getMovieDetail
    }

    fun getRequestMovieDetailState(movie: Movie, storage: Boolean?) {

        getMovieDetail.value = StateLoadMovie.Loading


        if (storage!!) {
            RemoteRepositoryImpl().getMovieDetail(movie, object : Callback<ImdbMovieDetail> {
                override fun onResponse(call: Call<ImdbMovieDetail>, response: Response<ImdbMovieDetail>) {
                    val movieDetail = response.body()
                    if (movieDetail != null) getMovieDetail.postValue(
                        StateLoadMovie.SuccessLoad(movieDetail)
                    )
                }
                override fun onFailure(call: Call<ImdbMovieDetail>, t: Throwable) {
                    getMovieDetail.postValue(StateLoadMovie.ErrorLoad(ImdbMovieDetail()))
                }

            })
        } else {
            LocalRepositoryImpl().getMovieDetail(movie, object : MovieDetailCallback {
                override fun onResponse(imdbMovieDetail: ImdbMovieDetail) {
                    getMovieDetail.postValue(StateLoadMovie.SuccessLoad(imdbMovieDetail))
                }
            })
        }
    }
}
package com.dk.newprojectsearchmovie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.model.imdbMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.repository.RemoteRepositoryImpl
import com.dk.newprojectsearchmovie.model.imdb.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(
    private var getMovieDetail: MutableLiveData<StateLoadMovie> = MutableLiveData()
) : ViewModel() {


    private val repository = RemoteRepositoryImpl()

    fun getMovieDetailState(): MutableLiveData<StateLoadMovie> {
        return getMovieDetail
    }

    fun getRequestMovieDetailState(movie: Movie) {

        getMovieDetail.value = StateLoadMovie.Loading

        repository.getMovieDetail(movie, object : Callback<ImdbMovieDetail> {
            override fun onResponse(
                call: Call<ImdbMovieDetail>, response: Response<ImdbMovieDetail>
            ) {
                val movieDetail = response.body()
                if (movieDetail != null) getMovieDetail.postValue(
                    StateLoadMovie.SuccessLoad(movieDetail)
                )
            }

            override fun onFailure(call: Call<ImdbMovieDetail>, t: Throwable) {
                getMovieDetail.postValue(StateLoadMovie.ErrorLoad(ImdbMovieDetail()))
            }

        })

    }
}
package com.dk.newprojectsearchmovie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.MovieInteractor
import com.dk.newprojectsearchmovie.model.imdb.Movie

class MovieDetailViewModel(
    private var getMovieDetail: MutableLiveData<StateLoadMovie> = MutableLiveData()
): ViewModel() {

    fun getMovieDetailState(): MutableLiveData<StateLoadMovie> {
        return getMovieDetail
    }

    fun getRequestMovieDetailState(movie: Movie, storage: Boolean) {
        getMovieDetail.value = StateLoadMovie.Loading
        Thread {
            val movieDetail = MovieInteractor(storage).getDetail(movie)
            getMovieDetail.postValue(movieDetail?.let { StateLoadMovie.SuccessLoad(it) })
        }.start()
    }
}
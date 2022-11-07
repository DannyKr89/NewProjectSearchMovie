package com.dk.newprojectsearchmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.LocalRepositoryImpl
import com.dk.newprojectsearchmovie.domain.MovieListType
import com.dk.newprojectsearchmovie.domain.RemoteRepositoryImpl
import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.getMoviePopularListFromLocalStorage
import com.dk.newprojectsearchmovie.model.getMovieTop250ListFromLocalStorage
import com.dk.newprojectsearchmovie.model.imdb.imdbMovie.ImdbMovieDetail

class MovieListViewModel(
    private var getMovieListTop250: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var getMovieListPopular: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var isRemoteStorage: MutableLiveData<Boolean> = MutableLiveData(),
    private var getMovieDetail: MutableLiveData<StateLoadMovie> = MutableLiveData()
) :
    ViewModel() {
    init {
        isRemoteStorage.value = false
        getMovieListTop250.value = StateLoadMovieList.SuccessLoad(getMovieTop250ListFromLocalStorage())
        getMovieListPopular.value = StateLoadMovieList.SuccessLoad(getMoviePopularListFromLocalStorage())
    }

    fun getLocalStorage(): MutableLiveData<Boolean> {
        return isRemoteStorage
    }

    fun toggleStorage() {
        val toggleStorage = getLocalStorage().value
        isRemoteStorage.value = !toggleStorage!!
    }


    fun getMovieListState(movieListType: MovieListType): MutableLiveData<StateLoadMovieList> {
        return when (movieListType) {
            MovieListType.TOP250 -> getMovieListTop250
            MovieListType.POPULAR -> getMovieListPopular
        }
    }

    fun getRequestMovieListState(movieListType: MovieListType) {
        val mutableLiveData = when (movieListType) {
            MovieListType.TOP250 -> getMovieListTop250
            MovieListType.POPULAR -> getMovieListPopular
        }
        mutableLiveData.value = StateLoadMovieList.Loading

        val repository = if (getLocalStorage().value == true) {
            RemoteRepositoryImpl()

        } else {
            LocalRepositoryImpl()
        }

        Thread {
            val movieList = repository.getMovieList(movieListType)
            if (movieList.isNotEmpty()) {
                mutableLiveData.postValue(
                    StateLoadMovieList.SuccessLoad(movieList)
                )
            } else {
                mutableLiveData.postValue(StateLoadMovieList.ErrorLoad(movieList))
            }
        }.start()
    }

    fun getMovieDetailState(): MutableLiveData<StateLoadMovie> {
        return getMovieDetail
    }

    fun getRequestMovieDetailState(movie: Movie) {
        getMovieDetail.value = StateLoadMovie.Loading
        val repository = if (getLocalStorage().value == true) {
            RemoteRepositoryImpl()
        } else {
            LocalRepositoryImpl()
        }
        Thread {
            val imdbMovieDetail = repository.getMovieDetail(movie)
            if (imdbMovieDetail.equals(ImdbMovieDetail())) {
                getMovieDetail.postValue(StateLoadMovie.ErrorLoad(imdbMovieDetail))
            } else {
                getMovieDetail.postValue(StateLoadMovie.SuccessLoad(imdbMovieDetail))
            }
        }.start()
    }


}
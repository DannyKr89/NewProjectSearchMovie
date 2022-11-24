package com.dk.newprojectsearchmovie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.domain.MovieInteractor

class MovieListViewModel(
    private var getMovieListTop250: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var getMovieListPopular: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var isRemoteStorage: MutableLiveData<Boolean> = MutableLiveData()

    ) : ViewModel() {
    init {
        isRemoteStorage.value = false
    }

    fun getLocalStorage() = isRemoteStorage


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
        Thread {
            val movieList = MovieInteractor(getLocalStorage().value!!).getList(movieListType)
            mutableLiveData.postValue(movieList?.let { StateLoadMovieList.SuccessLoad(it.movies!!) })
        }.start()
    }
}
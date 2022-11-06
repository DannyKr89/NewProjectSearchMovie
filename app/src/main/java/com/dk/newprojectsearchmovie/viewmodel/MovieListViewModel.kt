package com.dk.newprojectsearchmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.LocalRepositoryImpl
import com.dk.newprojectsearchmovie.domain.MovieListType
import com.dk.newprojectsearchmovie.domain.RemoteRepositoryImpl

class MovieListViewModel(
    private var getMovieListTop250: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var getMovieListPopular: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var isRemoteStorage: MutableLiveData<Boolean> = MutableLiveData()
) :
    ViewModel() {
    init {
        isRemoteStorage.value = false
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

    fun getRequestMovieListState(repositoryStorage: Boolean, movieListType: MovieListType) {
        val mutableLiveData = when (movieListType) {
            MovieListType.TOP250 -> getMovieListTop250
            MovieListType.POPULAR -> getMovieListPopular
        }
        mutableLiveData.value = StateLoadMovieList.Loading

        val repository = if (repositoryStorage) {
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
}
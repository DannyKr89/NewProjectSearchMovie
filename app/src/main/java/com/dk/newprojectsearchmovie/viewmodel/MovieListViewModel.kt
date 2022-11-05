package com.dk.newprojectsearchmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.LocalRepositoryImpl
import com.dk.newprojectsearchmovie.domain.RemoteRepositoryImpl

class MovieListViewModel(
    private var getMovieListTop250: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var getMovieListPopular: MutableLiveData<StateLoadMovieList> = MutableLiveData()
) :
    ViewModel() {
    init {
        getMovieListTop250.value = StateLoadMovieList.Loading
    }

    private var isLocal = true

    private val repository = if (false) {
        RemoteRepositoryImpl().also {
            isLocal = false
        }
    } else {
        LocalRepositoryImpl().also {
            isLocal = true
        }
    }


    fun getMovieListTop250State(): MutableLiveData<StateLoadMovieList> {
        return getMovieListTop250
    }

    fun getRequestMovieListTop250State() {
        getMovieListTop250.value = StateLoadMovieList.Loading
        Thread {
            if (isLocal) {
                getMovieListTop250.postValue(
                    StateLoadMovieList.SuccessLoad(repository.getMovieTop250List())
                )
            } else {
                getMovieListTop250.postValue(StateLoadMovieList.ErrorLoad(Throwable("Ошибка загрузки")))
            }
        }.start()

    }

    fun getMovieListPopularState(): MutableLiveData<StateLoadMovieList> {
        return getMovieListPopular
    }

    fun getRequestMovieListPopularState() {
        getMovieListPopular.value = StateLoadMovieList.Loading
        Thread {
            if (isLocal) {
                getMovieListPopular.postValue(
                    StateLoadMovieList.SuccessLoad(repository.getMoviePopularList())
                )
            } else {
                getMovieListPopular.postValue(StateLoadMovieList.ErrorLoad(Throwable("Ошибка загрузки")))
            }
        }.start()

    }
}
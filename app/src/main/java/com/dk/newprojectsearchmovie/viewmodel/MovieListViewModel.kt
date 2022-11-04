package com.dk.newprojectsearchmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.LocalRepositoryImpl
import com.dk.newprojectsearchmovie.domain.RemoteRepositoryImpl

class MovieListViewModel(private var getMovieList: MutableLiveData<StateLoadMovieList> = MutableLiveData()) :
    ViewModel() {
    init {
        getMovieList.value = StateLoadMovieList.Loading
    }

    private val repository = if (false) {
        RemoteRepositoryImpl()
    } else {
        LocalRepositoryImpl()
    }


    fun getMovieListState(): MutableLiveData<StateLoadMovieList> {
        return getMovieList
    }

    fun getRequestMovieListState() {
        getMovieList.value = StateLoadMovieList.Loading
        Thread {
            if ((0..3).random() == 3) {
                getMovieList.postValue(StateLoadMovieList.ErrorLoad(Throwable("Ошибка загрузки")))
            } else {
                getMovieList.postValue(
                    StateLoadMovieList.SuccesLoad(
                        repository.getMovieTop250List()
                    )
                )
            }
        }

    }
}
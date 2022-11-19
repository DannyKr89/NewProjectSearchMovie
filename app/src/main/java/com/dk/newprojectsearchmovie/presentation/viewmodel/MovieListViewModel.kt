package com.dk.newprojectsearchmovie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.common.MovieListCallback
import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.data.repository.LocalRepositoryImpl
import com.dk.newprojectsearchmovie.data.repository.RemoteRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(
    private var getMovieListTop250: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var getMovieListPopular: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var getMovieDetail: MutableLiveData<StateLoadMovie> = MutableLiveData(),
    private var isRemoteStorage: MutableLiveData<Boolean> = MutableLiveData(),

    ) : ViewModel() {
    init {
        isRemoteStorage.value = false
        getMovieListTop250.value = StateLoadMovieList.Loading
        getMovieListPopular.value = StateLoadMovieList.Loading
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

    fun getRequestMovieListState(movieListType: MovieListType, storage: Boolean?) {
        val mutableLiveData = when (movieListType) {
            MovieListType.TOP250 -> getMovieListTop250
            MovieListType.POPULAR -> getMovieListPopular
        }
        mutableLiveData.value = StateLoadMovieList.Loading


        if (storage!!) {
            RemoteRepositoryImpl().getMovieList(movieListType, object : Callback<ImdbMovieList> {
                override fun onResponse(
                    call: Call<ImdbMovieList>, response: Response<ImdbMovieList>
                ) {
                    val imdbMovieList = response.body()
                    if (imdbMovieList != null) mutableLiveData.postValue(
                        StateLoadMovieList.SuccessLoad(
                            imdbMovieList
                        )
                    )
                }

                override fun onFailure(call: Call<ImdbMovieList>, t: Throwable) {
                    mutableLiveData.postValue(StateLoadMovieList.ErrorLoad(ImdbMovieList(listOf())))
                }
            })
        } else {
            LocalRepositoryImpl().getMovieList(movieListType, object : MovieListCallback {
                override fun onResponse(imdbMovieList: ImdbMovieList) {
                    mutableLiveData.postValue(StateLoadMovieList.SuccessLoad(imdbMovieList))
                }
            })
        }
    }
}
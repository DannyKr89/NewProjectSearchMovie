package com.dk.newprojectsearchmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.*
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdb.Movie
import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail
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

    fun getRequestMovieListState(movieListType: MovieListType) {
        val mutableLiveData = when (movieListType) {
            MovieListType.TOP250 -> getMovieListTop250
            MovieListType.POPULAR -> getMovieListPopular
        }
        mutableLiveData.value = StateLoadMovieList.Loading


        if (getLocalStorage().value == true) {
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

                override fun onFailure() {

                }
            })
        }

    }

    fun getMovieDetailState(): MutableLiveData<StateLoadMovie> {
        return getMovieDetail
    }

    fun getRequestMovieDetailState(movie: Movie) {

        getMovieDetail.value = StateLoadMovie.Loading


        if (getLocalStorage().value == true) {
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
                override fun onFailure() {
                }

            })
        }
    }
}
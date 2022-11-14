package com.dk.newprojectsearchmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.MovieListType
import com.dk.newprojectsearchmovie.domain.RemoteRepositoryImpl
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
    private val repository: RemoteRepositoryImpl = RemoteRepositoryImpl()
) :
    ViewModel() {
    init {
        getMovieListTop250.value = StateLoadMovieList.Loading
        getMovieListPopular.value = StateLoadMovieList.Loading
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

        repository.getMovieList(movieListType, object : Callback<ImdbMovieList> {
            override fun onResponse(call: Call<ImdbMovieList>, response: Response<ImdbMovieList>) {
                val imdbMovieList = response.body()
                if (imdbMovieList != null)
                    mutableLiveData.postValue(StateLoadMovieList.SuccessLoad(imdbMovieList))
            }

            override fun onFailure(call: Call<ImdbMovieList>, t: Throwable) {
                mutableLiveData.postValue(StateLoadMovieList.ErrorLoad(ImdbMovieList(listOf())))
            }
        })

    }

    fun getMovieDetailState(): MutableLiveData<StateLoadMovie> {
        return getMovieDetail
    }

    fun getRequestMovieDetailState(movie: Movie) {
        getMovieDetail.value = StateLoadMovie.Loading
        repository.getMovieDetail(movie, object : Callback<ImdbMovieDetail> {
            override fun onResponse(
                call: Call<ImdbMovieDetail>,
                response: Response<ImdbMovieDetail>
            ) {
                val movieDetail = response.body()
                if (movieDetail != null)
                    getMovieDetail.postValue(StateLoadMovie.SuccessLoad(movieDetail))
            }

            override fun onFailure(call: Call<ImdbMovieDetail>, t: Throwable) {
                getMovieDetail.postValue(StateLoadMovie.ErrorLoad(ImdbMovieDetail()))
            }

        })
    }
}
package com.dk.newprojectsearchmovie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.common.MovieListType
import com.dk.newprojectsearchmovie.data.repository.RemoteRepositoryImpl
import com.dk.newprojectsearchmovie.domain.MovieInteractor
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(
    private var getMovieListTop250: MutableLiveData<StateLoadMovieList> = MutableLiveData(),
    private var getMovieListPopular: MutableLiveData<StateLoadMovieList> = MutableLiveData()

    ) : ViewModel() {

    private val repository = RemoteRepositoryImpl()
    private val movieInteractor = MovieInteractor(repository)

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
        movieInteractor.getList(movieListType, object: Callback<ImdbMovieList>{
            override fun onResponse(call: Call<ImdbMovieList>, response: Response<ImdbMovieList>) {
                val imdbMovieList = response.body()
                if (imdbMovieList != null && response.isSuccessful)
                    mutableLiveData.postValue(StateLoadMovieList.SuccessLoad(imdbMovieList))
            }

            override fun onFailure(call: Call<ImdbMovieList>, t: Throwable) {
                mutableLiveData.postValue(StateLoadMovieList.ErrorLoad(ImdbMovieList(listOf())))            }
        })

    }
}
package com.dk.newprojectsearchmovie.presentation.view.collections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.domain.MovieInteractor
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovieList
import com.dk.newprojectsearchmovie.presentation.view.states.StateLoadMovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectionsViewModel(private var getMovieList: MutableLiveData<StateLoadMovieList> = MutableLiveData()
) : ViewModel() {

    fun getMovieListState(): MutableLiveData<StateLoadMovieList> {
        return getMovieList
    }

    fun getRequestMovieListState(movieListType: String) {
        getMovieList.value = StateLoadMovieList.Loading

        MovieInteractor().getList(movieListType, object : Callback<CollectionsMovieList> {
            override fun onResponse(
                call: Call<CollectionsMovieList>,
                response: Response<CollectionsMovieList>
            ) {

                val movieList = response.body()
                if (response.isSuccessful && movieList != null) {
                    getMovieList.postValue(
                        movieList.movies.let { StateLoadMovieList.SuccessLoad(it) })
                } else {
                    getMovieList.postValue(StateLoadMovieList.ErrorLoad(Throwable("error")))
                }
            }

            override fun onFailure(call: Call<CollectionsMovieList>, t: Throwable) {
                getMovieList.postValue(StateLoadMovieList.ErrorLoad(t))
            }
        })
    }
}
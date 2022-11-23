package com.dk.newprojectsearchmovie.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.domain.MovieInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieSearchListViewModel(
    private val getMovieSearchListState: MutableLiveData<StateLoadMovieList> = MutableLiveData()
) : ViewModel() {

    private var minRating = "0.0,10.0"


    fun setMinRating(rating: Double){
        minRating = "$rating,10.0"
    }

    fun getMovieSearchList(): MutableLiveData<StateLoadMovieList> {
        return getMovieSearchListState
    }

    fun getMovieSearchRequest(titleQuery: String) {
        getMovieSearchListState.value = StateLoadMovieList.Loading

        MovieInteractor(true).getSearchList(titleQuery,minRating, object : Callback<RequestSearchMovie>{
            override fun onResponse(
                call: Call<RequestSearchMovie>,
                response: Response<RequestSearchMovie>
            ) {
                val searchList = response.body()
                if (response.isSuccessful){
                    getMovieSearchList().postValue(searchList?.let { StateLoadMovieList.SuccessLoad(it) })
                }
            }

            override fun onFailure(call: Call<RequestSearchMovie>, t: Throwable) {
                getMovieSearchListState.postValue(StateLoadMovieList.ErrorLoad(t))
            }
        })


    }


}
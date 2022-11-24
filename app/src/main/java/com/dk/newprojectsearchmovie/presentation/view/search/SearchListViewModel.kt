package com.dk.newprojectsearchmovie.presentation.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import com.dk.newprojectsearchmovie.domain.MovieInteractor
import com.dk.newprojectsearchmovie.presentation.view.states.StateLoadMovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchListViewModel(
    private val getMovieSearchListState: MutableLiveData<StateLoadMovieList> = MutableLiveData()
) : ViewModel() {


    fun getMovieSearchList(): MutableLiveData<StateLoadMovieList> {
        return getMovieSearchListState
    }

    fun getMovieSearchRequest(titleQuery: String) {
        getMovieSearchListState.value = StateLoadMovieList.Loading

        MovieInteractor().getSearchList(titleQuery, object : Callback<RequestSearchMovie>{
            override fun onResponse(
                call: Call<RequestSearchMovie>,
                response: Response<RequestSearchMovie>
            ) {
                val searchList = response.body()
                if (response.isSuccessful){
                    getMovieSearchList().postValue(searchList?.let {
                        StateLoadMovieList.SuccessLoad(
                            it.searchMovie
                        )
                    })
                }
            }

            override fun onFailure(call: Call<RequestSearchMovie>, t: Throwable) {
                getMovieSearchListState.postValue(StateLoadMovieList.ErrorLoad(t))
            }
        })
    }

}
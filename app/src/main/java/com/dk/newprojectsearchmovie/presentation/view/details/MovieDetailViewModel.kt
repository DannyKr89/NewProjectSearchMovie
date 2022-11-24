package com.dk.newprojectsearchmovie.presentation.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dk.newprojectsearchmovie.data.model.detailMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.domain.MovieInteractor
import com.dk.newprojectsearchmovie.presentation.view.states.StateLoadMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(
    private var getMovieDetail: MutableLiveData<StateLoadMovie> = MutableLiveData()
): ViewModel() {

    fun getMovieDetailState() = getMovieDetail


    fun getRequestMovieDetailState(movieID: String) {
        getMovieDetail.value = StateLoadMovie.Loading
        MovieInteractor().getDetail(movieID, object : Callback<ImdbMovieDetail> {
            override fun onResponse(
                call: Call<ImdbMovieDetail>,
                response: Response<ImdbMovieDetail>
            ) {
                val movieDetail = response.body()
                if (movieDetail != null && response.isSuccessful){
                    getMovieDetail.postValue(StateLoadMovie.SuccessLoad(movieDetail))
                }
            }

            override fun onFailure(call: Call<ImdbMovieDetail>, t: Throwable) {
                getMovieDetail.postValue(StateLoadMovie.ErrorLoad(t))
            }
        })

    }
}
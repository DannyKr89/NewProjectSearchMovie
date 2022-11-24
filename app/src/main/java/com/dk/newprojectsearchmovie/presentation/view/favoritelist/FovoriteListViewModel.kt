package com.dk.newprojectsearchmovie.presentation.view.favoritelist

import androidx.lifecycle.ViewModel

class FovoriteListViewModel(
//    private var getMovieList: MutableLiveData<StateLoadMovieList> = MutableLiveData(),

    ) : ViewModel() {


//    fun getMovieListState(): MutableLiveData<StateLoadMovieList> {
//        return getMovieList
//    }
//
//    fun getRequestMovieListState(movieListType: String) {
//        getMovieList.value = StateLoadMovieList.Loading
//
//        MovieInteractor().getList(movieListType, object : Callback<CollectionsMovieList> {
//            override fun onResponse(
//                call: Call<CollectionsMovieList>,
//                response: Response<CollectionsMovieList>
//            ) {
//
//                val movieList = response.body()
//                if (response.isSuccessful && movieList != null) {
//                    getMovieList.postValue(
//                        movieList.movies.let { StateLoadMovieList.SuccessLoad(it) })
//                } else {
//                    getMovieList.postValue(StateLoadMovieList.ErrorLoad(Throwable("error")))
//                }
//            }
//
//            override fun onFailure(call: Call<CollectionsMovieList>, t: Throwable) {
//                getMovieList.postValue(StateLoadMovieList.ErrorLoad(t))
//            }
//        })
//    }
}
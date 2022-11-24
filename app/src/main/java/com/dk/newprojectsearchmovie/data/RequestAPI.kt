package com.dk.newprojectsearchmovie.data

import com.dk.newprojectsearchmovie.data.common.API_KEY
import com.dk.newprojectsearchmovie.model.imdb.CollectionsMovieList
import com.dk.newprojectsearchmovie.data.model.detailMovie.ImdbMovieDetail
import com.dk.newprojectsearchmovie.data.model.search.RequestSearchMovie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface RequestAPI {

    @GET("ru/API/AdvancedSearch/$API_KEY/")
    fun getSearchMovieList(
        @Query("title") titleQuery: String,
        @Query("title_type") titleType: String = "feature",
        @Query("user_rating") rating: String = "0.0,10.0"
    ) : Call<RequestSearchMovie>

    @GET("ru/API/{movieList}/$API_KEY")
    fun getMovieList(
        @Path("movieList")movieList: String
    ) : Call<CollectionsMovieList>

    @GET("ru/API/Title/$API_KEY/{movieID}")
    fun getMovieDetail(
        @Path("movieID") movieID: String
    ): Call<ImdbMovieDetail>

    companion object {
        fun create(): RequestAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://imdb-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RequestAPI::class.java)
        }
    }
}

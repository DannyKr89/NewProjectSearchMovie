package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.dk.newprojectsearchmovie.model.imdbMovie.ImdbMovieDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "k_u5wvreo1"
private const val API_KEY2 = "k_wj3nygya"

interface RequestAPI {

    @GET("ru/API/{movieList}/$API_KEY")
    fun getMovieList(
        @Path("movieList")movieList: String
    ) : Call<ImdbMovieList>

    @GET("ru/API/Title/$API_KEY/{movieID}")
    fun getMovieDetail(
        @Path("movieID") movieID: String
    ): Call<ImdbMovieDetail>

    companion object {
        fun create(): RequestAPI{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://imdb-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RequestAPI::class.java)
        }
    }
}

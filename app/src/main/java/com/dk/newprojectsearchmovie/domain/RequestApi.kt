package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie
import com.dk.newprojectsearchmovie.model.imdb.ImdbMovieList
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RequestApi {

    companion object {
        const val TOP250 = "Top250Movies"
        const val POPULAR = "MostPopularMovies"
        private const val API_KEY = "k_u5wvreo1"

        fun requestMovieList(movieListType: MovieListType): List<Movie> {
            val movieList = when (movieListType) {
                MovieListType.TOP250 -> TOP250
                MovieListType.POPULAR -> POPULAR
            }
            val list: MutableList<Movie> = mutableListOf()
            val url = URL("https://imdb-api.com/ru/API/$movieList/$API_KEY")
            val connection = url.openConnection() as HttpsURLConnection
            connection.readTimeout = 10000
            return try {
                val inputStream = connection.inputStream
                val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
                val imdbMovieList = Gson().fromJson(inputStreamReader, ImdbMovieList::class.java)
                inputStreamReader.close()
                inputStream.close()
                imdbMovieList.movies?.forEach {
                    list.add(Movie(it.title, it.year, it.imDbRating, it.image, id = it.id))
                }
                list
            } catch (e: Exception) {
                println()
                listOf<Movie>()
            } finally {
                connection.disconnect()
            }

        }
    }
}
package com.dk.newprojectsearchmovie.domain

import com.dk.newprojectsearchmovie.model.Movie

class RemoteRepositoryImpl: Repository {
    override fun getMovieTop250List(): List<Movie> {
        return listOf()
    }

    override fun getMoviePopularList(): List<Movie> {
        return listOf()
    }
}
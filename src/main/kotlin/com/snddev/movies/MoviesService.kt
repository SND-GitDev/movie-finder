package com.snddev.movies

import javax.inject.Named


@Named
class MoviesService(
    private val moviesClient: MoviesClient
){
    fun getGenres() : List<Genre>{
        return moviesClient.getGenres().genres
    }

    fun getMovies() : List<Movie>{
        return moviesClient.getMovies().results.map {
            Movie(
                genreIds = it.genreIds,
                title = it.title
            )
        }
    }
}

data class Genre(
    val id: Long,
    val name: String
)

data class Movie(
    val genreIds : List<Long>,
    val title: String
)

package com.snddev.movies

import javax.inject.Named


@Named
class MoviesService(
    private val moviesClient: MoviesClient
) {
    fun getGenres(): List<Genre> {
        return genresCache[GENRES_CACHE_KEY] ?: listOf()
    }

    fun getMovies(): List<Movie> {
        return moviesCache[MOVIES_CACHE_KEY] ?: listOf()
    }

    fun syncGenres() {
        val genres = moviesClient.getGenres().genres
        genresCache[GENRES_CACHE_KEY] = genres
    }

    fun syncMovies() {
        val movies = moviesClient.getMovies().results.mapToMovie()
        moviesCache[MOVIES_CACHE_KEY] = movies
    }

    private fun List<TMDBMovie>.mapToMovie() = this.map {
        Movie(
            genreIds = it.genreIds,
            title = it.title,
            posterPath = it.posterPath,
            overview = it.overview
        )
    }
}

data class Genre(
    val id: Long,
    val name: String
)

data class Movie(
    val genreIds: List<Long>,
    val title: String,
    val posterPath: String,
    val overview: String
)

const val GENRES_CACHE_KEY = "genres"
const val MOVIES_CACHE_KEY = "movies"

val genresCache = cacheInstance.getMap<String, List<Genre>>(GENRES_CACHE_KEY)
val moviesCache = cacheInstance.getMap<String, List<Movie>>(MOVIES_CACHE_KEY)
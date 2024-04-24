package com.snddev.movies

import javax.inject.Named


@Named
class MoviesService(
    private val moviesClient: MoviesClient
) {
    fun getGenres(): List<Genre> {
        return genresCache[GENRES_CACHE_KEY] ?: listOf()
    }

    fun getMovies(): Map<Long, List<Movie>> = moviesCache

    fun getMovies(page: Long): Map<Long, List<Movie>> {
        val movies = moviesCache[page]
        return movies?.let { mapOf(page to it) }
            ?: mapOf(page to listOf())
    }

    fun syncGenres(): List<Genre> {
        val genres = moviesClient.getGenres().genres
        genresCache[GENRES_CACHE_KEY] = genres
        return getGenres()
    }

    fun syncMovies(page: Long): Map<Long, List<Movie>> {
        val response = moviesClient.getMovies(page)
        moviesCache[response.page] = response.results.mapToMovie()
        return getMovies(page)
    }

    private fun List<TMDBMovie>.mapToMovie(): List<Movie> = this.map {
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
val moviesCache = cacheInstance.getMap<Long, List<Movie>>(MOVIES_CACHE_KEY)
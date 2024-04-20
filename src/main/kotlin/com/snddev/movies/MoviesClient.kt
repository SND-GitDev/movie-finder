package com.snddev.movies

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import javax.inject.Named

val tmdbApiKey: String = System.getenv("TMDB_API_KEY") ?: error("TMDB_API_KEY missing from environment variables...")
private const val rootTMDBURL = "https://api.themoviedb.org"

@Named
class MoviesClient {
    fun getGenres(): TMDBGenres {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add(
            "Authorization",
            "Bearer $tmdbApiKey"
        )
        val restTemplate = RestTemplate()
        val request = HttpEntity("", headers)
        val response = restTemplate.exchange(
            "$rootTMDBURL/3/genre/movie/list",
            HttpMethod.GET,
            request,
            TMDBGenres::class.java
        )
        return response.body ?: TMDBGenres(genres = listOf())
    }

    fun getMovies(): TMDBDiscoverResponse {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add(
            "Authorization",
            "Bearer $tmdbApiKey"
        )
        val restTemplate = RestTemplate()
        val request = HttpEntity("", headers)
        val response = restTemplate.exchange(
            "$rootTMDBURL/3/discover/movie",
            HttpMethod.GET,
            request,
            TMDBDiscoverResponse::class.java
        )
        return response.body ?: TMDBDiscoverResponse(
            page = 1,
            results = listOf()
        )
    }
}

data class TMDBGenres(
    @JsonProperty("genres")
    val genres: List<Genre>
)

data class TMDBDiscoverResponse(
    @JsonProperty("page")
    val page: Long,
    @JsonProperty("results")
    val results: List<TMDBMovie>
)

data class TMDBMovie(
    @JsonProperty("genre_ids")
    val genreIds : List<Long>,
    @JsonProperty("title")
    val title: String
)
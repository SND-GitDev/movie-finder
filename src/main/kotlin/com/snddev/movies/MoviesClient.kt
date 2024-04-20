package com.snddev.movies

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
}
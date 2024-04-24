package com.snddev.movies

import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MoviesController(
    private val moviesService: MoviesService
) {
    @GetMapping(
        path = ["/movies/v1/genres/list"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getGenres(): ResponseEntity<*> {
        val genres = moviesService.getGenres()
        return ResponseEntity(genres, HttpStatusCode.valueOf(200))
    }

    @GetMapping(
        path = ["/movies/v1/genres/list/sync"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun syncGenres(): ResponseEntity<*> {
        return ResponseEntity(moviesService.syncGenres(), HttpStatusCode.valueOf(200))
    }

    @GetMapping(
        path = ["/movies/v1/movies/list"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getMovies(): ResponseEntity<*> {
        val movies = moviesService.getMovies()
        return ResponseEntity(movies, HttpStatusCode.valueOf(200))
    }

    @GetMapping(
        path = ["/movies/v1/movies/list/{page}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getMoviesPage(@PathVariable page: Long): ResponseEntity<*> {
        val movies = moviesService.getMovies(page)
        return ResponseEntity(movies, HttpStatusCode.valueOf(200))
    }

    @GetMapping(
        path = ["/movies/v1/movies/list/{page}/sync"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun syncMovies(@PathVariable page: Long): ResponseEntity<*> {
        return ResponseEntity(moviesService.syncMovies(page), HttpStatusCode.valueOf(200))
    }
}

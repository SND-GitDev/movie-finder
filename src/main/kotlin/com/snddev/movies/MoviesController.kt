package com.snddev.movies

import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MoviesController(
    private val moviesService: MoviesService
){
    @GetMapping(
        path = ["/movies/v1/genres/list"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getGenres(): ResponseEntity<*> {
        val genres = moviesService.getGenres()
        return ResponseEntity(genres, HttpStatusCode.valueOf(200))
    }
}
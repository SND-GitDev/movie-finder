package com.snddev.movies

import com.fasterxml.jackson.annotation.JsonProperty
import javax.inject.Named


@Named
class MoviesService(
    private val moviesClient: MoviesClient
){
    fun getGenres() : List<Genre>{
        return moviesClient.getGenres().genres
    }
}

data class Genre(
    val id: Long,
    val name: String
)

data class TMDBGenres(
    @JsonProperty("genres")
    val genres: List<Genre>
)
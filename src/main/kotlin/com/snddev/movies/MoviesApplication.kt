package com.snddev.movies

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoviesApplication

fun main(args: Array<String>) {
	runApplication<MoviesApplication>(*args)
}


const val HAZLECAST_INSTANCE_NAME = "moviesInstance"
val cacheInstance: HazelcastInstance = Hazelcast.newHazelcastInstance(Config(HAZLECAST_INSTANCE_NAME))

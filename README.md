# Movie Finder App

This is the codebase the Movie Finder Application built in kotlin & spring-boot

# How to Run?

1) Open intelliJ
2) Navigate to src/main/kotlin/com/snddev/movies/MoviesApplication.kt
3) Create a configuration for the main() function by right-clicking the green arrow in the gutter to the left of the
   function and clicking "Modify Run Configuration..."
4) In the Run/Debug Configuration window that appears, go to the Environment Variables
5) Create TMDB_API_KEY variable and paste the Api Key from TMDB as the value
6) Run the configuration

This should run a server at localhost:8080 with a Hazelcast Cache

# Sample cURL Commands:
Make sure the server is running

Always start with the `sync` commands to populate the cache

Populate the cache with:
```bash
curl --location --request GET localhost:8080/movies/v1/genres/list/sync
```
```bash
curl --location --request GET localhost:8080/movies/v1/movies/list/sync
```

List the contents from the cache with:
```bash
curl --location --request GET localhost:8080/movies/v1/genres/list
```
```bash
curl --location --request GET localhost:8080/movies/v1/movies/list
```

# Current Features:
1) Synchronize & Cache Genres
2) Synchronize & Cache Movies
3) List Genres & Movies

# To Be Developed
1) Filter movies by Genre
2) Search for a Movie
3) Rate a Movie
4) Whitelist & Favourite a Movie
5) Add Persistence via Database
6) Create unit tests
7) Maintain 90% Coverage
8) Containerize the app via Docker
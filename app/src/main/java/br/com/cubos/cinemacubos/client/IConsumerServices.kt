package br.com.cubos.cinemacubos.client

import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IConsumerServices {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("with_genres") genres: Int
    ): Single<Response>

    @GET("3/genre/movie/list")
    fun getGenres(
        @Query("api_key") key: String,
        @Query("language") language: String
    ): Single<Response>

    @GET("3/movie/{id}}")
    fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): Single<Movie>

    @GET("3/search/movie")
    fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): Single<Response>
}

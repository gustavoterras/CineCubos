package br.com.cubos.cinemacubos.client

import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IConsumerServices {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") key: String,
        @Query("with_genres") genres: Int): Single<Response>
}
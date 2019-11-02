package br.com.cubos.cinemacubos.ui.main

import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single

interface MainRepository {
    fun getRemoteMoviesByGenre(genreId: Int): Single<Response>
}
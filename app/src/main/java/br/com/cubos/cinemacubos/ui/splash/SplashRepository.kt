package br.com.cubos.cinemacubos.ui.splash

import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single

interface SplashRepository {
    fun getRemoteGenres(): Single<Response>
}
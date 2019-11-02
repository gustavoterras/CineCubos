package br.com.cubos.cinemacubos.ui.splash

import br.com.cubos.cinemacubos.entries.Genres

interface SplashView {
    fun onSuccess(genres: List<Genres>)
    fun onError()
}

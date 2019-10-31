package br.com.cubos.cinemacubos.ui.splash

import br.com.cubos.cinemacubos.entries.Movie

interface SplashView {

    fun showMovies(movies: List<Movie>)
    fun onError()

}
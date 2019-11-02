package br.com.cubos.cinemacubos.ui.main

interface MainPresenter {
    fun getMoviesByGenre(genreId: Int)
    fun onDisposed()
}
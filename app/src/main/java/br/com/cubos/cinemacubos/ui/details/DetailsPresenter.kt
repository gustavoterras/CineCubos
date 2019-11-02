package br.com.cubos.cinemacubos.ui.details

interface DetailsPresenter {
    fun getMoviesDetailsById(movieId: Int)
    fun onDisposed()
}
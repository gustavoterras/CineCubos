package br.com.cubos.cinemacubos.ui.search

interface SearchPresenter {
    fun searchMoviesByKeyword(keyword: String)
    fun onDisposed()
}

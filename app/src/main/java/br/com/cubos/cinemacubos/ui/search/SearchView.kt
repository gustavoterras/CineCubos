package br.com.cubos.cinemacubos.ui.search

import br.com.cubos.cinemacubos.entries.Movie

interface SearchView {
    fun onSuccess(movies: ArrayList<Movie>)
    fun onError()
}

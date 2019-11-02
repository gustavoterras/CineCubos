package br.com.cubos.cinemacubos.ui.main

import br.com.cubos.cinemacubos.entries.Movie

interface MainView {
    fun onSuccess(movies: ArrayList<Movie>)
    fun onError()
}
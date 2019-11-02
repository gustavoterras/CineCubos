package br.com.cubos.cinemacubos.ui.details

import br.com.cubos.cinemacubos.entries.Movie

interface DetailsView {
    fun onSuccess(movie: Movie)
    fun onLoading()
    fun onError()
}

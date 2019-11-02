package br.com.cubos.cinemacubos.ui.details

import br.com.cubos.cinemacubos.entries.Movie
import io.reactivex.Single

interface DetailsRepository {
    fun getRemoteDetails(movieId: Int): Single<Movie>
}

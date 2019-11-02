package br.com.cubos.cinemacubos.ui.details

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.entries.Movie
import io.reactivex.Single
import java.util.Locale

class DetailsRepositoryImp(private val consumerServices: ConsumerServices) : DetailsRepository {
    override fun getRemoteDetails(movieId: Int): Single<Movie> {
        return consumerServices
            .getClient()
            .getMovieDetails(
                movieId,
                "e3c33bce9383efa1016fab44818b8003",
                Locale.getDefault().toString()
            )
    }
}
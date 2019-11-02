package br.com.cubos.cinemacubos.ui.main

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single
import java.util.Locale

class MainRepositoryImp(private val consumerServices: ConsumerServices) : MainRepository {
    override fun getRemoteMoviesByGenre(genreId: Int): Single<Response> {
        return consumerServices
            .getClient()
            .getMovies(
                "e3c33bce9383efa1016fab44818b8003",
                Locale.getDefault().toString(),
                genreId
            )
    }
}

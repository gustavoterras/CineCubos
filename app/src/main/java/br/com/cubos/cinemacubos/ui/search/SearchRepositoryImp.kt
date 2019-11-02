package br.com.cubos.cinemacubos.ui.search

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single
import java.util.Locale

class SearchRepositoryImp(private val consumerServices: ConsumerServices) : SearchRepository {
    override fun searchRemote(keyword: String): Single<Response> {
        return consumerServices
            .getClient()
            .searchMovies(
                keyword,
                "e3c33bce9383efa1016fab44818b8003",
                Locale.getDefault().toString()
            )
    }
}

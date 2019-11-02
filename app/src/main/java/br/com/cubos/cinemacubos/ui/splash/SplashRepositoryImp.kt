package br.com.cubos.cinemacubos.ui.splash

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single
import java.util.Locale

class SplashRepositoryImp(private val consumerServices: ConsumerServices) : SplashRepository {

    override fun getRemoteGenres(): Single<Response> {
        return consumerServices
            .getClient()
            .getGenres("e3c33bce9383efa1016fab44818b8003", Locale.getDefault().toString())
    }
}
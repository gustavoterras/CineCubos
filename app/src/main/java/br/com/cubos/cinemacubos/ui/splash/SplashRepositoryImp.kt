package br.com.cubos.cinemacubos.ui.splash

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.entries.Response
import io.reactivex.Single

class SplashRepositoryImp(private val consumerServices: ConsumerServices) : SplashRepository {

    override fun getRemoteMovies(): Single<Response> {
        return consumerServices
            .getClient()
            .getMovies("e3c33bce9383efa1016fab44818b8003***", 28)
    }
}
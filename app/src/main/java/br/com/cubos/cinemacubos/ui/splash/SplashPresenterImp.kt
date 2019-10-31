package br.com.cubos.cinemacubos.ui.splash

import br.com.cubos.cinemacubos.utils.saveMainThread
import io.reactivex.rxkotlin.subscribeBy

class SplashPresenterImp(private val view: SplashView, private val repository: SplashRepository) : SplashPresenter {

    override fun getMovies() {
        repository
            .getRemoteMovies()
            .saveMainThread()
            .subscribeBy(
                onSuccess = {
                    view.showMovies(it.results)
                },
                onError = {
                    view.onError()
                }
            )
    }
}
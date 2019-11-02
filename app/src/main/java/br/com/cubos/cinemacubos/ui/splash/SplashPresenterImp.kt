package br.com.cubos.cinemacubos.ui.splash

import br.com.cubos.cinemacubos.utils.disposeBag
import br.com.cubos.cinemacubos.utils.saveMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class SplashPresenterImp(private val view: SplashView, private val repository: SplashRepository) : SplashPresenter {

    companion object {
        const val DELAY = 3000L
    }

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getGenres() {
        repository
            .getRemoteGenres()
            .delay(DELAY, TimeUnit.MILLISECONDS)
            .saveMainThread()
            .subscribeBy(
                onSuccess = {
                    view.onSuccess(it.genres)
                },
                onError = {
                    view.onError()
                }
            ).disposeBag(compositeDisposable)
    }

    override fun onDisposed() {
        compositeDisposable.dispose()
    }
}

package br.com.cubos.cinemacubos.ui.details

import br.com.cubos.cinemacubos.utils.disposeBag
import br.com.cubos.cinemacubos.utils.saveMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class DetailsPresenterImp(private val view: DetailsView, private val repository: DetailsRepository) : DetailsPresenter {

    companion object {
        const val DELAY = 1000L
    }

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getMoviesDetailsById(movieId: Int) {
        repository
            .getRemoteDetails(movieId)
            .delay(DELAY, TimeUnit.MILLISECONDS)
            .doOnSubscribe { view.onLoading() }
            .saveMainThread()
            .subscribeBy(
                onSuccess = {
                    view.onSuccess(it)
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

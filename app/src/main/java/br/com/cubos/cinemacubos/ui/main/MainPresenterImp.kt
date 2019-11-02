package br.com.cubos.cinemacubos.ui.main

import br.com.cubos.cinemacubos.utils.disposeBag
import br.com.cubos.cinemacubos.utils.saveMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainPresenterImp(private val view: MainView, private val repository: MainRepository) : MainPresenter {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getMoviesByGenre(genreId: Int) {
        repository
            .getRemoteMoviesByGenre(genreId)
            .saveMainThread()
            .subscribeBy(
                onSuccess = {
                    view.onSuccess(it.results)
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
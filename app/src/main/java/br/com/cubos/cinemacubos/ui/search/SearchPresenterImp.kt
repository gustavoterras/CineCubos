package br.com.cubos.cinemacubos.ui.search

import br.com.cubos.cinemacubos.utils.disposeBag
import br.com.cubos.cinemacubos.utils.saveMainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class SearchPresenterImp(private val view: SearchView, private val repository: SearchRepository) :
    SearchPresenter {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun searchMoviesByKeyword(keyword: String) {
        repository
            .searchRemote(keyword)
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

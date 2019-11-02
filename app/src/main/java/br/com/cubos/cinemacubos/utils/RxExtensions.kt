package br.com.cubos.cinemacubos.utils

import androidx.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

private fun <T> Observable<T>.subscribeOnIo(): Observable<T> = subscribeOn(Schedulers.io())
private fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.saveMainThread(): Observable<T> = subscribeOnIo().observeOnMainThread()

private fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())
private fun <T> Single<T>.observeOnMainThread(): Single<T> =
    observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.saveMainThread(): Single<T> = subscribeOnIo().observeOnMainThread()

fun Disposable.disposeBag(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> ObservableField<T>.toObservable(): Observable<T> {
    return Observable.create { emitter ->
        val initialValue = this.get()

        initialValue?.let { emitter.onNext(it) }

        val callback = object : androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: androidx.databinding.Observable, i: Int) {
                this@toObservable.get()?.let { emitter.onNext(it) }
            }
        }

        this.addOnPropertyChangedCallback(callback)

        emitter.setCancellable { this.removeOnPropertyChangedCallback(callback) }
    }
}

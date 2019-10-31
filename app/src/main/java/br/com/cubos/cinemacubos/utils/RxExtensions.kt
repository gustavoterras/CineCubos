package br.com.cubos.cinemacubos.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

private fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())
private fun <T> Single<T>.observeOnMainThread(): Single<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.saveMainThread(): Single<T> = subscribeOnIo().observeOnMainThread()
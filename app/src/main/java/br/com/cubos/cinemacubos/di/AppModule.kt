package br.com.cubos.cinemacubos.di

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.ui.splash.*
import org.koin.dsl.module

val appModule = module {

    single { ConsumerServices() }
    single<SplashRepository> { SplashRepositoryImp(get()) }
    factory<SplashPresenter> { (view: SplashView) -> SplashPresenterImp(view, get()) }
}
package br.com.cubos.cinemacubos.di

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.ui.details.*
import br.com.cubos.cinemacubos.ui.main.*
import br.com.cubos.cinemacubos.ui.search.*
import br.com.cubos.cinemacubos.ui.splash.*
import org.koin.dsl.module

val appModule = module {
    single { ConsumerServices() }
    single<SplashRepository> { SplashRepositoryImp(get()) }
    factory<SplashPresenter> { (view: SplashView) -> SplashPresenterImp(view, get()) }
    single<MainRepository> { MainRepositoryImp(get()) }
    factory<MainPresenter> { (view: MainView) -> MainPresenterImp(view, get()) }
    single<DetailsRepository> { DetailsRepositoryImp(get()) }
    factory<DetailsPresenter> { (view: DetailsView) -> DetailsPresenterImp(view, get()) }
    single<SearchRepository> { SearchRepositoryImp(get()) }
    factory<SearchPresenter> { (view: SearchView) -> SearchPresenterImp(view, get()) }
}

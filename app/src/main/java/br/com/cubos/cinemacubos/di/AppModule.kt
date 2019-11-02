package br.com.cubos.cinemacubos.di

import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.ui.details.DetailsPresenter
import br.com.cubos.cinemacubos.ui.details.DetailsPresenterImp
import br.com.cubos.cinemacubos.ui.details.DetailsRepository
import br.com.cubos.cinemacubos.ui.details.DetailsRepositoryImp
import br.com.cubos.cinemacubos.ui.details.DetailsView
import br.com.cubos.cinemacubos.ui.main.MainPresenter
import br.com.cubos.cinemacubos.ui.main.MainPresenterImp
import br.com.cubos.cinemacubos.ui.main.MainRepository
import br.com.cubos.cinemacubos.ui.main.MainRepositoryImp
import br.com.cubos.cinemacubos.ui.main.MainView
import br.com.cubos.cinemacubos.ui.search.SearchPresenter
import br.com.cubos.cinemacubos.ui.search.SearchPresenterImp
import br.com.cubos.cinemacubos.ui.search.SearchRepository
import br.com.cubos.cinemacubos.ui.search.SearchRepositoryImp
import br.com.cubos.cinemacubos.ui.search.SearchView
import br.com.cubos.cinemacubos.ui.splash.SplashPresenter
import br.com.cubos.cinemacubos.ui.splash.SplashPresenterImp
import br.com.cubos.cinemacubos.ui.splash.SplashRepository
import br.com.cubos.cinemacubos.ui.splash.SplashRepositoryImp
import br.com.cubos.cinemacubos.ui.splash.SplashView
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

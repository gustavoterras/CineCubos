package br.com.cubos.cinemacubos.base

import android.app.Application
import br.com.cubos.cinemacubos.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.mockito.Mockito

open class BaseTest {

    private val mockedAndroidContext: Application = Mockito.mock(Application::class.java)

    init {
        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidContext(mockedAndroidContext)
            modules(appModule)
        }
    }
}

package br.com.cubos.cinemacubos.features.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.ui.splash.SplashPresenterImp
import br.com.cubos.cinemacubos.ui.splash.SplashRepository
import br.com.cubos.cinemacubos.ui.splash.SplashView
import io.mockk.MockKAnnotations.init
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SplashPresenterTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var mockView: SplashView

    @MockK
    private lateinit var mockRepository: SplashRepository

    private var presenter: SplashPresenterImp? = null

    @Before
    fun setUp() {
        init(this)

        mockkStatic("br.com.cubos.cinemacubos.utils.RxExtensionsKt")

        presenter = SplashPresenterImp(mockView, mockRepository)
    }

    @After
    fun tearDown() {
        presenter?.onDisposed()
    }

    @Test
    fun `must set initial state`() {
        assertNotNull(presenter)
    }
}

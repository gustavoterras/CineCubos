package br.com.cubos.cinemacubos.features.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.ui.main.MainPresenterImp
import br.com.cubos.cinemacubos.ui.main.MainRepository
import br.com.cubos.cinemacubos.ui.main.MainView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainPresenterTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var mockView: MainView

    @MockK
    private lateinit var mockRepository: MainRepository

    private var presenter: MainPresenterImp? = null

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockkStatic("br.com.cubos.cinemacubos.utils.RxExtensionsKt")

        presenter = MainPresenterImp(mockView, mockRepository)
    }

    @After
    fun tearDown() {
        presenter?.onDisposed()
    }

    @Test
    fun `must set initial state`() {
        Assert.assertNotNull(presenter)
    }
}
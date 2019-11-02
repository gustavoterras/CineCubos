package br.com.cubos.cinemacubos.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.ui.details.DetailsPresenterImp
import br.com.cubos.cinemacubos.ui.details.DetailsRepository
import br.com.cubos.cinemacubos.ui.details.DetailsView
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
class DetailsPresenterTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var mockView: DetailsView

    @MockK
    private lateinit var mockRepository: DetailsRepository

    private var presenter: DetailsPresenterImp? = null

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockkStatic("br.com.cubos.cinemacubos.utils.RxExtensionsKt")

        presenter = DetailsPresenterImp(mockView, mockRepository)
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
package br.com.cubos.cinemacubos.features.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.ui.search.SearchPresenterImp
import br.com.cubos.cinemacubos.ui.search.SearchRepository
import br.com.cubos.cinemacubos.ui.search.SearchView
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
class SearchPresenterTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var mockView: SearchView

    @MockK
    private lateinit var mockRepository: SearchRepository

    private var presenter: SearchPresenterImp? = null

    @Before
    fun setUp() {
        init(this)

        mockkStatic("br.com.cubos.cinemacubos.utils.RxExtensionsKt")

        presenter = SearchPresenterImp(mockView, mockRepository)
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

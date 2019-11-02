package br.com.cubos.cinemacubos.features.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.ui.splash.SplashRepositoryImp
import io.mockk.MockKAnnotations.init
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SplashRepositoryTest {

    @MockK
    private lateinit var consumerServices: ConsumerServices

    private var repository: SplashRepositoryImp? = null

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        init(this)

        every {
            consumerServices.getClient()
        } returns mockk()

        every {
            consumerServices.getClient().getGenres(any(), any())
        } returns mockk()

        repository = SplashRepositoryImp(consumerServices)
    }

    @Test
    fun `must set initial state`() {
        assertNotNull(repository)
    }

    @Test
    fun `when get all genres`() {
        val result = repository?.getRemoteGenres()

        assertNotNull(result)
    }
}
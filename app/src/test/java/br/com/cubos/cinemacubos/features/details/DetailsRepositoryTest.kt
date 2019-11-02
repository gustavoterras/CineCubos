package br.com.cubos.cinemacubos.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.client.ConsumerServices
import br.com.cubos.cinemacubos.ui.details.DetailsRepositoryImp
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
class DetailsRepositoryTest {

    @MockK
    private lateinit var consumerServices: ConsumerServices

    private var repository: DetailsRepositoryImp? = null

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
            consumerServices.getClient().getMovieDetails(any(), any(), any())
        } returns mockk()

        repository = DetailsRepositoryImp(consumerServices)
    }

    @Test
    fun `must set initial state`() {
        assertNotNull(repository)
    }

    @Test
    fun `when get movie details by movie id`() {
        val result = repository?.getRemoteDetails(1)

        assertNotNull(result)
    }
}

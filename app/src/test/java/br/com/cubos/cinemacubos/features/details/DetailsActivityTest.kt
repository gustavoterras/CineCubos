package br.com.cubos.cinemacubos.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.ui.details.DetailsActivity
import br.com.cubos.cinemacubos.utils.Constants.BUNDLE_KEY
import io.mockk.MockKAnnotations.init
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DetailsActivityTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var activity: DetailsActivity

    @Before
    fun setUp() {
        init(this)

        activity = Robolectric.buildActivity(DetailsActivity::class.java).get().apply {
            intent.putExtra(BUNDLE_KEY, mockk<Movie>())
        }
    }

    @Test
    fun `must set initial state`() {
        assertNotNull(activity)
        assertNotNull(activity.movie)
        assertNotNull(activity.loading)
        assertNotNull(activity.success)
        assertNotNull(activity.companiesAdapter)
    }

}
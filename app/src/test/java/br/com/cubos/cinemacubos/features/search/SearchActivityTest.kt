package br.com.cubos.cinemacubos.features.search

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.ui.search.SearchActivity
import br.com.cubos.cinemacubos.utils.Constants.BUNDLE_KEY
import io.mockk.MockKAnnotations.init
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class SearchActivityTest : AutoCloseKoinTest() {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var activity: SearchActivity

    @Before
    fun setUp() {
        init(this)

        activity = Robolectric.buildActivity(SearchActivity::class.java).get().apply {
            intent.putExtra(BUNDLE_KEY, mockk<Movie>())
        }
    }

    @Test
    fun `must set initial state`() {
        assertNotNull(activity)
        assertNotNull(activity.presenter)
        assertNotNull(activity.textSearch)
        assertNotNull(activity.searchAdapter)
    }
}

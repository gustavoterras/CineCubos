package br.com.cubos.cinemacubos.features.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cubos.cinemacubos.base.BaseTest
import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.ui.splash.SplashActivity
import br.com.cubos.cinemacubos.utils.Constants.BUNDLE_KEY
import io.mockk.MockKAnnotations.init
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SplashActivityTest : BaseTest() {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var activity: SplashActivity

    @Before
    fun setUp() {
        init(this)

        activity = Robolectric.buildActivity(SplashActivity::class.java).get().apply {
            intent.putExtra(BUNDLE_KEY, mockk<Movie>())
        }
    }

    @Test
    fun `must set initial state`() {
        assertNotNull(activity)
        assertNotNull(activity.presenter)
    }

    @After
    fun teardown() {
        stopKoin()
    }
}

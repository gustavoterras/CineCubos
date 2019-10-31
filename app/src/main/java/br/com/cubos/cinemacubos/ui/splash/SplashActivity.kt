package br.com.cubos.cinemacubos.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import br.com.cubos.cinemacubos.R
import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.ui.error.ErrorActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashView {

    private val presenter: SplashPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ViewDataBinding>(this, R.layout.activity_splash)

        presenter.getMovies()
    }

    override fun showMovies(movies: List<Movie>) {
        Log.e("a", movies.size.toString())
    }

    override fun onError() {
        startActivity(Intent(this, ErrorActivity::class.java))
    }
}

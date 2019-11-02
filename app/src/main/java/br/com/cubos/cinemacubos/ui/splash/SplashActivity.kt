package br.com.cubos.cinemacubos.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import br.com.cubos.cinemacubos.R
import br.com.cubos.cinemacubos.entries.Genres
import br.com.cubos.cinemacubos.ui.error.ErrorActivity
import br.com.cubos.cinemacubos.ui.main.MainActivity
import br.com.cubos.cinemacubos.utils.Constants
import br.com.cubos.cinemacubos.utils.Constants.BUNDLE_KEY
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity(), SplashView {

    val presenter: SplashPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ViewDataBinding>(this, R.layout.activity_splash)

        presenter.getGenres()
    }

    override fun onSuccess(genres: List<Genres>) {
        startActivity(Intent(this, MainActivity::class.java).apply {
            putParcelableArrayListExtra(BUNDLE_KEY, ArrayList(genres))
        })
    }

    override fun onError() {
        startActivityForResult(Intent(this, ErrorActivity::class.java), Constants.ERROR_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.getGenres()
    }

    override fun onDestroy() {
        presenter.onDisposed()
        super.onDestroy()
    }
}

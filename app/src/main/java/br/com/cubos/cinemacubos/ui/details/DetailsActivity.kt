package br.com.cubos.cinemacubos.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import br.com.cubos.cinemacubos.BR
import br.com.cubos.cinemacubos.R
import br.com.cubos.cinemacubos.adapter.RecyclerBindingAdapter
import br.com.cubos.cinemacubos.entries.Companies
import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.ui.error.ErrorActivity
import br.com.cubos.cinemacubos.utils.Constants
import br.com.cubos.cinemacubos.utils.Constants.BUNDLE_KEY
import br.com.cubos.cinemacubos.utils.load
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsActivity : AppCompatActivity(), DetailsView {

    private val presenter: DetailsPresenter by inject { parametersOf(this) }

    val companiesAdapter =
        RecyclerBindingAdapter<Companies>(R.layout.item_companies, BR.item, arrayListOf())

    val loading = ObservableBoolean(false)
    val success = ObservableBoolean(false)

    var movie = ObservableField<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val movie: Movie = intent.getSerializableExtra(BUNDLE_KEY) as Movie

        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_details).apply {
            setVariable(BR.movie, movie)
            setVariable(BR.loading, loading)
            setVariable(BR.success, success)
            setVariable(BR.onClick, View.OnClickListener { actionOpenHomePage() })
        }

        supportPostponeEnterTransition()
        movie.let {
            movie_poster.load(it.poster_path) {
                supportStartPostponedEnterTransition()
            }
        }

        movie_companies_list.layoutManager = FlexboxLayoutManager(this).apply {
            flexDirection = FlexDirection.COLUMN
            flexWrap = FlexWrap.WRAP
            justifyContent = JustifyContent.CENTER
        }

        movie_companies_list.adapter = companiesAdapter

        this.movie.set(movie)
        this.presenter.getMoviesDetailsById(movie.id)
    }

    fun actionOpenHomePage() {
        this.movie.get()?.homepage?.let {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        movie.get()?.id?.let { presenter.getMoviesDetailsById(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            supportFinishAfterTransition()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSuccess(movie: Movie) {
        loading.set(false)
        success.set(true)

        companiesAdapter.setList(movie.production_companies)

        this.movie.set(movie)
    }

    override fun onLoading() {
        loading.set(true)
    }

    override fun onError() {
        startActivityForResult(Intent(this, ErrorActivity::class.java), Constants.ERROR_CODE)
        loading.set(false)
        success.set(false)
    }
}

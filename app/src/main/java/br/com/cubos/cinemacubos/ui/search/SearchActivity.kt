package br.com.cubos.cinemacubos.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import br.com.cubos.cinemacubos.BR
import br.com.cubos.cinemacubos.R
import br.com.cubos.cinemacubos.adapter.RecyclerBindingAdapter
import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.ui.details.DetailsActivity
import br.com.cubos.cinemacubos.ui.error.ErrorActivity
import br.com.cubos.cinemacubos.utils.Constants
import br.com.cubos.cinemacubos.utils.saveMainThread
import br.com.cubos.cinemacubos.utils.toObservable
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_search.search_list
import kotlinx.android.synthetic.main.item_movie.view.movie_poster
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchActivity : AppCompatActivity(), SearchView {

    private val presenter: SearchPresenter by inject { parametersOf(this) }

    val textSearch = ObservableField<String>()
    val searchAdapter = RecyclerBindingAdapter<Movie>(R.layout.item_movie_search, BR.item, arrayListOf())

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ViewDataBinding>(this, R.layout.activity_search).apply {
                setVariable(BR.keyword, textSearch)
                setVariable(BR.onClick, View.OnClickListener { finishAfterTransition() })
            }

        textSearch.toObservable()
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .saveMainThread()
            .subscribe { result ->
                presenter.searchMoviesByKeyword(result)
            }

        search_list.adapter = searchAdapter

        searchAdapter.setOnItemClickListener(object : RecyclerBindingAdapter.OnItemClickListener<Movie> {
            override fun onItemClick(position: Int, view: View, item: Movie) {
                val intent = Intent(this@SearchActivity, DetailsActivity::class.java)
                intent.putExtra(Constants.BUNDLE_KEY, item)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@SearchActivity,
                    view.movie_poster,
                    "movie"
                )
                startActivity(intent, options.toBundle())
            }
        })
    }

    override fun onSuccess(movies: ArrayList<Movie>) {
        searchAdapter.setList(movies)
    }

    override fun onError() {
        startActivity(Intent(this, ErrorActivity::class.java))
    }
}

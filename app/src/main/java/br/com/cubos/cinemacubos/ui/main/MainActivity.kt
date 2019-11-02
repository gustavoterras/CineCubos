package br.com.cubos.cinemacubos.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import br.com.cubos.cinemacubos.BR
import br.com.cubos.cinemacubos.R
import br.com.cubos.cinemacubos.adapter.RecyclerBindingAdapter
import br.com.cubos.cinemacubos.entries.Genres
import br.com.cubos.cinemacubos.entries.Movie
import br.com.cubos.cinemacubos.ui.details.DetailsActivity
import br.com.cubos.cinemacubos.ui.error.ErrorActivity
import br.com.cubos.cinemacubos.ui.search.SearchActivity
import br.com.cubos.cinemacubos.utils.Constants.BUNDLE_KEY
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_main.rv_genres
import kotlinx.android.synthetic.main.activity_main.rv_movies
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by inject { parametersOf(this) }

    val genresAdapter = RecyclerBindingAdapter<Genres>(R.layout.item_genre, BR.item, arrayListOf())
    val moviesAdapter = RecyclerBindingAdapter<Movie>(R.layout.item_movie, BR.item, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)

        val genres = intent.getParcelableArrayListExtra<Genres>(BUNDLE_KEY)

        genresAdapter.setList(genres)

        rv_genres.layoutManager = getLayoutManager(this).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
        }
        rv_movies.layoutManager = getLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
            justifyContent = JustifyContent.CENTER
        }

        rv_genres.adapter = genresAdapter
        rv_movies.adapter = moviesAdapter

        genresAdapter.setOnItemClickListener(object :
            RecyclerBindingAdapter.OnItemClickListener<Genres> {
            override fun onItemClick(position: Int, view: View, item: Genres) {
                presenter.getMoviesByGenre(item.id)

                genresAdapter.getItems().forEach { g ->
                    g.selected = g.id == item.id
                }
            }
        })

        moviesAdapter.setOnItemClickListener(object :
            RecyclerBindingAdapter.OnItemClickListener<Movie> {
            override fun onItemClick(position: Int, view: View, item: Movie) {

                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra(BUNDLE_KEY, item)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@MainActivity,
                    view.movie_poster,
                    "movie"
                )
                startActivity(intent, options.toBundle())
            }
        })

        presenter.getMoviesByGenre(genres[0].id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        takeIf { item.itemId == R.id.bar_search }?.run {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getLayoutManager(context: Context): FlexboxLayoutManager {
        return FlexboxLayoutManager(context)
    }

    override fun onSuccess(movies: ArrayList<Movie>) {
        moviesAdapter.setList(movies)
    }

    override fun onError() {
        startActivity(Intent(this, ErrorActivity::class.java))
    }
}

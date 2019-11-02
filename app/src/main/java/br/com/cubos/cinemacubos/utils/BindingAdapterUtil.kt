package br.com.cubos.cinemacubos.utils

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.AnimRes
import androidx.databinding.BindingAdapter
import br.com.cubos.cinemacubos.R
import br.com.cubos.cinemacubos.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object BindingAdapterUtil {

    @JvmStatic
    @BindingAdapter("image_url")
    fun imageUrl(view: ImageView, path: String?) {
        Glide
            .with(view)
            .load(IMAGE_URL + path.orEmpty())
            .placeholder(R.drawable.popcorn_placeholder)
            .error(R.drawable.popcorn_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("animation")
    fun animation(view: View, @AnimRes animationRes: Int) {
        val animation = AnimationUtils.loadAnimation(view.context, animationRes)
        view.startAnimation(animation)
    }
}
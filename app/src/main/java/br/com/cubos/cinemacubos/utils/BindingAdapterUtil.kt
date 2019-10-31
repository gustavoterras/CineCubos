package br.com.cubos.cinemacubos.utils

import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.databinding.BindingAdapter

object BindingAdapterUtil {

    @BindingAdapter("animation")
    fun animation(view: View, @AnimRes animationRes: Int) {
        val animation = AnimationUtils.loadAnimation(view.context, animationRes)
        view.startAnimation(animation)
    }

}
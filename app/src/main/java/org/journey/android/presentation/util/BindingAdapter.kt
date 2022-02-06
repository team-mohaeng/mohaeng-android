package org.journey.android.presentation.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import org.journey.android.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setImageURL")
    fun ImageView.setImageURL(url: String?) { if(!url.isNullOrEmpty()) load(url) }

    @JvmStatic
    @BindingAdapter("setCharacterImageURL")
    fun ImageView.setCharacterImageURL(url: String?) {
        if(!url.isNullOrEmpty()) load(url) else setImageDrawable(ContextCompat.getDrawable(context, R.drawable.stylecardlock1))
    }

    @JvmStatic
    @BindingAdapter("corner_radius")
    fun ShapeableImageView.setCornerRadius(radius: Float) {
        shapeAppearanceModel = ShapeAppearanceModel().withCornerSize(radius)
    }

    @JvmStatic
    @BindingAdapter("loadDrawable")
    fun loadDrawable(imageView: ImageView, drawable: Int?) {
        if (drawable == null) {
            imageView.load(R.drawable.ic_launcher_background)
        } else {
            imageView.load(drawable)
        }
    }

    @JvmStatic
    @BindingAdapter("setBackgroundColorRes")
    fun View.setBackgroundColorRes(color: Int) {
        setBackgroundColor(context.getColor(color))
    }
}
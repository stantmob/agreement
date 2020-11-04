package com.example.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

class ImageViewBindingAdapter {

    @BindingAdapter(value = ["iconId", "iconColorId"], requireAll = false)
    fun ImageView.setIconWithColor(iconId: Int?, iconColorId: Int?) {
        val drawable = iconId?.let { ContextCompat.getDrawable(context, it) }

        drawable?.let {
            iconColorId?.let { iconColorId ->
                setDrawableColorFilter(
                    context,
                    drawable,
                    iconColorId
                )
            }
            setImageDrawable(drawable)
        }
    }

    private fun setDrawableColorFilter(context: Context, drawable: Drawable, colorId: Int) {
        drawable.mutate()
            .setColorFilter(ContextCompat.getColor(context, colorId), PorterDuff.Mode.SRC_ATOP)
    }


}
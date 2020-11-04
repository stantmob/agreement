package com.example.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

fun setDrawableColorFilter(context: Context, drawable: Drawable, colorId: Int) {
        drawable.mutate().setColorFilter(ContextCompat.getColor(context, colorId), PorterDuff.Mode.SRC_ATOP)
    }
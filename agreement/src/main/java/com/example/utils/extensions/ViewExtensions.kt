package com.example.utils.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.example.agreement.R
import kotlin.math.roundToInt

fun View.getBitmapFromView(context: Context): Bitmap {
    val returnedBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888)
    val canvas         = Canvas(returnedBitmap)
    val bgDrawable     = this.background

    if (bgDrawable != null) {
        bgDrawable.draw(canvas)
    } else {
        canvas.drawColor(ContextCompat.getColor(context, R.color.white))
    }

    this.draw(canvas)

    return returnedBitmap
}

fun createBitmapFromView(view: View, width: Int, height: Int): Bitmap {
    if (width > 0 && height > 0) {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(
                                    convertDpToPixels(width.toFloat()), View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.makeMeasureSpec(
                    convertDpToPixels(height.toFloat()), View.MeasureSpec.EXACTLY
            )
        );
    }
    view.layout(0, 0, view.measuredWidth, view.measuredHeight);

    val bitmap = Bitmap.createBitmap(view.measuredWidth,
    view.measuredHeight, Bitmap.Config.ARGB_8888);
    val canvas = Canvas(bitmap)

    view.background?.draw(canvas)
    view.draw(canvas)

    return bitmap
}

fun convertDpToPixels(dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp, Resources.getSystem().displayMetrics
    ).roundToInt()
}
package com.example.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.agreement.R

class GlideUtils {

    companion object {

        fun putImageResourceOnImageView(imageView: ImageView?, url: String?, size: Int) {
            if (imageView != null) {
                Glide.with(imageView)
                    .asBitmap()
                    .load(url)
                    .placeholder(R.drawable.house)
                    .error(R.drawable.house)
                    .override(size)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            imageView.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            imageView.setImageDrawable(placeholder)
                        }
                    })
            }
            }
        }
    }




package com.example.drawview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.agreement.R
import com.example.agreement.databinding.DrawOnTouchCustomCanvasBinding
import com.example.listener.OnSavedPhotoListener
import com.example.utils.extensions.getBitmapFromView
import com.example.utils.saveBitmapInPictures

class CustomCanvasForDraw @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int        = 0
) : LinearLayout(context, attrs, defStyle) {

    private var mCustomDrawView: CustomDrawView? = null

    private var mBinding: DrawOnTouchCustomCanvasBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.draw_on_touch_custom_canvas, this, true
    )

    init {
        initView()
    }

    fun saveFigure(onSavedPhotoListener: OnSavedPhotoListener) {
        val customDrawViewBitmap = mBinding.root.getBitmapFromView(context)
        customDrawViewBitmap.saveBitmapInPictures(context, onSavedPhotoListener)
    }

    private fun initView() {
        mCustomDrawView = mBinding.mainView
    }

    fun resetView() {
        mCustomDrawView?.resetView()
    }


}
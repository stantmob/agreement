package com.example.agreement

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.listener.OnDrawSaveListener
import com.example.agreement.databinding.DrawModuleFragmentBinding
import com.example.listener.OnSavedPhotoListener

class Agreement @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyle: Int = 0) : FrameLayout(context, attributeSet, defStyle)
{
    private var mListener: OnDrawSaveListener? = null
    private var mPath: String? = null

    private val mBinding: DrawModuleFragmentBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
        R.layout.draw_module_fragment, this, true)

    init {

        mBinding.backButton.setOnClickListener { backPressed() }
        mBinding.cleanCanvaButton.setOnClickListener { resetView() }
        mBinding.forwardButton.setOnClickListener { saveDrawing() }
    }

    private fun resetView() {
        mBinding.customCanvas.resetView()
    }

    private fun saveDrawing() {
        disableButtons()
        mBinding.customCanvas.saveFigure(object : OnSavedPhotoListener {
            override fun onSaved(path: String) {
                mListener?.setOnSaveListener(path)
                mPath = path
                backPressed()
            }
        })
    }

    private fun disableButtons() {
        mBinding.drawModuleContentView.isEnabled = false
    }

    private fun backPressed() {

    }

    fun setForwardText(text: String) {
        mBinding.forwardText = text
    }

    fun setCleanText(text: String) {
        mBinding.cleanText = text
    }

    fun setForwardButtonColor(color: Int) {
        mBinding.colorForward = color
    }

    fun setCleanButtonColor(color: Int) {
        mBinding.colorClean = color
    }

    fun setOnDrawSaveListener(listener: OnDrawSaveListener) {
        mListener = listener
    }

    fun getPath() : String? {
    return mPath
    }

}


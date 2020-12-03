package com.example.agreement

import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.agreement.agreementdto.AddAgreementDto
import com.example.agreement.databinding.AddAgreementComponentBinding
import com.example.agreement.viewmodels.AddAgreementSharedViewModel
import com.example.listener.OnDrawSaveListener
import com.example.utils.extensions.ContextExtension.Companion.getActivity

class AddAgreementView @JvmOverloads constructor(
    context:  Context,
    attrs:    AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle){
    private var mAgreementData: AgreementDto?       = null
    private var mListener: OnDrawSaveListener?      = null
    private var model: AddAgreementSharedViewModel? = null

    private var mForwardText: String?     = null
    private var mEraseText: String?       = null
    private var mForwardTextColor: Int?   = null
    private var mEraseTextColor: Int?     = null
    private var mLeftButtonDrawable: Int? = null

    private val mBinding: AddAgreementComponentBinding = DataBindingUtil.inflate(
        LayoutInflater.from(
            context
        ),
        R.layout.add_agreement_component, this, true
    )

    init {
        model = ViewModelProviders.of(context as FragmentActivity).get(AddAgreementSharedViewModel::class.java)
        model?.getAgreementDto()?.observeForever {
            mAgreementData = it
        }
    }

    private fun openDrawDialog() {
        getActivity(context)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        if (context is FragmentActivity) {
            val fragmentTransaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
            val drawModuleFragment = DrawModuleDialogFragment()
            model?.sendAddAgreementDto(setAddAgreementDto())
            drawModuleFragment.show(fragmentTransaction, "drawAgreement")
        }
    }

    fun setOpenDialogClickListener() {
        mBinding.addAgreementComponentImageView.setOnClickListener {
            openDrawDialog()
        }
    }

    private fun setAddAgreementDto(): AddAgreementDto  =
        AddAgreementDto(
            forwardText        = mForwardText ?: resources.getString(R.string.custom_draw_forward_text),
            eraseText          = mEraseText ?: resources.getString(R.string.custom_draw_erase_text),
            forwardColor       = mForwardTextColor?: R.color.occurrence_gray,
            eraseColor         = mEraseTextColor?: R.color.occurrence_gray,
            leftButtonDrawable = mLeftButtonDrawable?: R.drawable.ic_arrow_left_grey
        )


    fun setForwardText(text: String?){
        mForwardText = text
    }

    fun setEraseText(text: String?){
        mEraseText = text
    }

    fun setForwardTextColor(color: Int?){
        mForwardTextColor = color
    }

    fun setEraseTextColor(color: Int?){
        mEraseTextColor = color
    }

    fun setLeftButtonDrawable(drawable: Int?){
        mLeftButtonDrawable = drawable
    }

    fun getAgreementData() : AgreementDto? {
        return mAgreementData
    }



}


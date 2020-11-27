package com.example.agreement

import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.example.agreement.agreementdto.AddAgreementDto
import com.example.agreement.databinding.AddAgreementComponentBinding
import com.example.agreement.previewpicture.PreviewDialog
import com.example.agreement.viewmodels.AddAgreementSharedViewModel
import com.example.listener.OnDataSaveListener
import com.example.listener.OnDrawSaveListener
import com.example.utils.extensions.ContextExtension.Companion.getActivity

class AddAgreementView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle){
    private var mAddAgreementDto: AddAgreementDto?  = null
    private var mAgreementData: AgreementDto?       = null
    private var mListener: OnDrawSaveListener?      = null
    private var model: AddAgreementSharedViewModel? = null
    private var preview= PreviewDialog()
    private val mBinding: AddAgreementComponentBinding = DataBindingUtil.inflate(
        LayoutInflater.from(
            context
        ),
        R.layout.add_agreement_component, this, true
    )

    init {
        model = ViewModelProvider(context as FragmentActivity).get(AddAgreementSharedViewModel::class.java)
        model?.getAgreementDto()?.observeForever {
            mAgreementData = it
        }
    }

    private fun openDrawDialog() {
        getActivity(context)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        if (context is FragmentActivity) {
            val fragmentTransaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
            val drawModuleFragment = DrawModuleDialogFragment()
            mAddAgreementDto?.let { model?.sendAddAgreementDto(it) }
            drawModuleFragment.show(fragmentTransaction, "drawAgreement")
        }
    }

    fun setOpenDialogClickListener() {
        mBinding.addAgreementComponentImageView.setOnClickListener {
            openDrawDialog()
        }
    }

    fun setAddAgreementDto(addAgreementDto: AddAgreementDto) {
        mAddAgreementDto = addAgreementDto
    }

    fun getAgreementData() : AgreementDto? {
        return mAgreementData
    }



}


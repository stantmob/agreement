package com.example.agreement

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.agreement.agreementdto.AddAgreementDto
import com.example.agreement.agreementdto.AgreementDto
import com.example.agreement.databinding.DrawModuleFragmentBinding
import com.example.agreement.previewpicture.PreviewDialog
import com.example.agreement.viewmodels.AddAgreementSharedViewModel
import com.example.agreement.viewmodels.SharedViewModel
import com.example.listener.OnDataSaveListener
import com.example.listener.OnSavedPhotoListener
import com.example.utils.permissions.AppPermissions
import com.example.utils.BaseDialog

class DrawModuleDialogFragment : BaseDialog() {

    private var mBinding: DrawModuleFragmentBinding?    = null
    private var model: SharedViewModel?                 = null
    private var viewmodel: AddAgreementSharedViewModel? = null
    private val mVisualizationDialog                    = PreviewDialog()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState, R.style.full_screen_dialog).also {
            it.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            mBinding = DataBindingUtil.inflate(inflater,
                R.layout.draw_module_fragment, container, true)
            setupView()

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model     = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        viewmodel = ViewModelProviders.of(requireActivity()).get(AddAgreementSharedViewModel::class.java)

        viewmodel?.getAddAgreementDto()?.observe(viewLifecycleOwner, { addAgreementDto->
                     fillViews(addAgreementDto)
        })
    }

    private fun setupView() {
        mBinding?.drawModuleFragmentBackButton?.setOnClickListener { backPressed() }
        mBinding?.drawModuleFragmentCleanCanvaButtonTextView?.setOnClickListener { resetView() }
        mBinding?.drawModuleFragmentForwardButton?.setOnClickListener { openPickGalleryIntent() }
    }

    private fun fillViews(addAgreementDto: AddAgreementDto) {
        mBinding?.dto = addAgreementDto
    }

    private fun resetView() {
        mBinding?.customCanvas?.resetView()
    }

    private fun saveDrawing() {
        disableButtons()
        mBinding?.customCanvas?.saveFigure(object : OnSavedPhotoListener {
            override fun onSaved(path: String) {
                model?.sendPath(path)
            }
        })
    }

    private fun openPickGalleryIntent() {
        if (!AppPermissions.hasPermissions(context)) {
            context?.let { AppPermissions.requestPermissions(it) }
        } else {
            saveDrawing()
            openDrawDialog()
        }
    }

    private fun openDrawDialog() {
        if (context is FragmentActivity) {
            val fragmentTransaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
            mVisualizationDialog.show(fragmentTransaction, "tag")
            dismiss()
        }
    }

    private fun disableButtons() {
        mBinding?.drawModuleFragmentContentView?.isEnabled = false
    }

    private fun backPressed() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        dismiss()
    }


}


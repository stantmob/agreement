package com.example.agreement.previewpicture

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.agreement.AgreementDto
import com.example.agreement.R
import com.example.agreement.databinding.PreviewDialogBinding
import com.example.agreement.viewmodels.AddAgreementSharedViewModel
import com.example.agreement.viewmodels.SharedViewModel
import com.example.listener.OnDataSaveListener
import com.example.utils.BaseDialog

class PreviewDialog : BaseDialog() {

    private var mBinding: PreviewDialogBinding? = null
    private var viewModel: SharedViewModel?     = null
    private var model: AddAgreementSharedViewModel?     = null
    private var mListener: OnDataSaveListener?  = null
    private var mPath: String?                  = null
    private var mCaption: String?               = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        return super.onCreateDialog(savedInstanceState, android.R.style.ThemeOverlay)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProviders.of(requireActivity()).get(AddAgreementSharedViewModel::class.java)
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        viewModel?.getPath()?.observe(viewLifecycleOwner, { path->
            mPath = path
            setupImage(path)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.preview_dialog,
                container,
                false
            )
        setupView()

        return mBinding?.root
    }

    private fun setupView() {
        configureDialog(dialog)
        configureDialogWindow(dialog?.window)
        configureBackButton()
        save()
    }

    private fun configureBackButton() {
        mBinding?.agreementPreviewDialogCloseIconImageView?.setOnClickListener {
            backPressed()
        }
    }

    private fun setupImage(path: String) {
            mBinding?.agreementPreviewDialogMainImageView?.let {imageView->
                context?.let { context ->
                    Glide.with(context)
                        .load(path)
                        .into(imageView)
                }
            }
        mBinding?.executePendingBindings()
    }

    private fun configureDialog(dialog: Dialog?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.let {
            it.setCancelable(true)
            it.setCanceledOnTouchOutside(true)

        }
    }

    private fun configureDialogWindow(dialogWindow: Window?) {
        dialogWindow?.let {
            it.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            it.setGravity(Gravity.CENTER)
        }
    }

    private fun backPressed() {
        dismiss()
    }

    fun setupListener(onSaveData: OnDataSaveListener) {
        mListener = onSaveData
    }

    private fun save() {
        mBinding?.cameraPhotoPreviewDialogEditCaption?.text.toString()
        mBinding?.saveTextView?.setOnClickListener {
            model?.sendAgreementDto(
                AgreementDto(
                    mPath,
                    mBinding?.cameraPhotoPreviewDialogEditCaption?.text.toString()
                )
            )
            mListener?.setOnDataSaveListener(
                AgreementDto(
                    mPath,
                    mBinding?.cameraPhotoPreviewDialogEditCaption?.text.toString()
                )
            )
            dismiss()

        }
    }


}
package com.example.utils

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

open class BaseDialog : DialogFragment() {

    fun onCreateDialog(savedInstanceState: Bundle?, themeResId: Int) =
        context?.let {
            Dialog(it, themeResId)
        } ?: super.onCreateDialog(savedInstanceState)


}
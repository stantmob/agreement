package com.example.utils.permissions

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.agreement.R
import com.example.utils.extensions.ContextExtension.Companion.getActivity


object AppPermissions {

    private const val doNotAskIsChecked = false
    private const val PERMISSIONS_CODE  = 1

    private var deniedPermissions: MutableList<String>? = null

    private val PERMISSIONS         = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    fun hasPermissions(context: Context?): Boolean {
        var result                                 = true
        val deniedPermissions: MutableList<String> = mutableListOf()

        for (permission in PERMISSIONS) {
            val permissionStatus = context?.let { ActivityCompat.checkSelfPermission(it, permission) }
            if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission)
                result = false
            }
        }
        AppPermissions.deniedPermissions = deniedPermissions
        deniedPermissions.add(AppPermissions.deniedPermissions.toString())
        return result
    }

    private val isNotApiAndroidM: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M

    fun requestPermissions(context: Context) {
        if (isNotApiAndroidM) return
        buildPermissionDialog(context).show()
    }

    private fun buildPermissionDialog(context: Context): Dialog {
        val informationDialog = Dialog(context)
        informationDialog.setContentView(R.layout.permission_information_dialog)

        if (donNotAskIsChecked()) {
            configureDialogs(
                context,
                informationDialog,
                View.VISIBLE,
                R.string.permission_information_dialog_never_checked_message
            )
        } else {
            configureDialogs(
                context,
                informationDialog,
                View.GONE,
                R.string.permission_information_dialog_message
            )
        }
        return informationDialog
    }

    private fun donNotAskIsChecked(): Boolean {
        return doNotAskIsChecked
    }

    private fun configureDialogs(
        context: Context,
        informationDialog: Dialog,
        visibility: Int,
        contentText: Int
    ) {
        val informationText = informationDialog.findViewById<TextView>(R.id.permission_information_dialog_text_view)
        val settingsButton  =
            informationDialog.findViewById<TextView>(R.id.permission_information_dialog_go_to_settings_text_view)
        val okButton        =
            informationDialog.findViewById<TextView>(R.id.permission_information_dialog_confirmation_text_view)

        informationText.text      = context.resources.getString(contentText)
        settingsButton.visibility = visibility

        addListeners(getActivity(context), informationDialog, okButton, settingsButton)
    }

    private fun addListeners(
        context: Context?,
        informationDialog: Dialog,
        okButton: TextView,
        settingsOption: TextView
    ) {
        okButton.setOnClickListener {
            informationDialog.dismiss()
            requestPermission(context)
        }

        settingsOption.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts(
                "package", getActivity(context)!!
                    .packageName, null
            )
            intent.data = uri
            getActivity(context)?.startActivityForResult(intent, 1)
        }
    }

    private fun requestPermission(context: Context?) {
        if (!hasPermissions(context) && deniedPermissions != null) {
            ActivityCompat.requestPermissions(
                getActivity(context)!!,
                deniedPermissions!!.toTypedArray(),
                PERMISSIONS_CODE
            )
            deniedPermissions = null
        } else if (!hasPermissions(context?.applicationContext) && deniedPermissions == null) {
            getActivity(context)?.let { activity->
                ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSIONS_CODE) }
        }
    }


}
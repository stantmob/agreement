package com.example.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.ContextWrapper

class ContextExtension {

    companion object {

        fun getActivity(context: Context?): AppCompatActivity? {
            if (context == null) {
                return null
            } else if (context is ContextWrapper) {
                return if (context is AppCompatActivity) {
                    context
                } else {
                    getActivity(context.baseContext)
                }
            }
            return null
        }
    }

}
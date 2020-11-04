package com.example.agreementSample

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.agreementSample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation =  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setup()
    }

    private fun setup() {
        setForwardText("Avançar")
        setCleanText("Apagar")
        setForwardButtonColor(R.color.occurrence_gray_forward)
        setCleanButtonColor(R.color.occurrence_gray_erase)
    }

    private fun setForwardText(text: String) {
        mBinding?.agreement?.setForwardText(text)
    }

    private fun setCleanText(text: String) {
        mBinding?.agreement?.setCleanText(text)
    }

    private fun setForwardButtonColor(color: Int) {
        mBinding?.agreement?.setForwardButtonColor(color)
    }

    private fun setCleanButtonColor(color: Int) {
        mBinding?.agreement?.setCleanButtonColor(color)
    }


}
package com.example.agreementSample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.agreement.AgreementDto
import com.example.agreement.agreementdto.AddAgreementDto
import com.example.agreementSample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private var mDto: AgreementDto?            = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setup()
    }

    private fun setup() {
        setData()
        openDialog()
        showButton()

    }

    private fun showButton() {
        mBinding?.newButton?.setOnClickListener {
        mDto = mBinding?.agreement?.getAgreementData()
        }
    }

    private fun setData() {
        mBinding?.agreement?.setForwardText("Avançar")
        mBinding?.agreement?.setEraseText("Apagar")
        mBinding?.agreement?.setForwardTextColor(R.color.grey)
        mBinding?.agreement?.setEraseTextColor(R.color.occurrence_gray)
        mBinding?.agreement?.setLeftButtonDrawable(null)
    }

    private fun openDialog() {
        mBinding?.agreement?.setOpenDialogClickListener()
    }


}
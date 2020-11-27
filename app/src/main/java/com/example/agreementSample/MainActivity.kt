package com.example.agreementSample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import com.example.agreement.AgreementDto
import com.example.agreement.agreementdto.AddAgreementDto
import com.example.agreementSample.databinding.ActivityMainBinding
import com.example.listener.OnDataSaveListener

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private var mDto: AgreementDto? = null
    private var teste: String? = null

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
        mBinding?.agreement?.setAddAgreementDto(
            AddAgreementDto(
                "Avançar", "Apagar",
                R.color.occurrence_gray_forward, R.color.occurrence_gray_erase, 0
            )
        )
    }

    private fun openDialog() {
        mBinding?.agreement?.setOpenDialogClickListener()
    }



}
package com.example.agreementSample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.agreement.agreementdto.AgreementDto
import com.example.agreementSample.databinding.ActivityMainBinding
import com.example.listener.OnDrawSaveListener

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
        setOnSaveListener()
    }

    private fun showButton() {
        mBinding?.agreement?.getAgreementData()

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

    private fun setOnSaveListener() {
        mBinding?.agreement?.configureListener(object: OnDrawSaveListener {
            override fun setOnSaveListener(dto: AgreementDto) {
                mBinding?.newText?.post {
                    dto.path?.let { configureA(it) }
                }

                mBinding?.newText2?.post {
                    dto.caption?.let { configureB(it) }
                }
            }

        })    }

    fun configureA(dto: String) {
        mBinding?.newText?.text = dto

    }

    fun configureB(dto: String){
        mBinding?.newText2?.text = dto
    }


}
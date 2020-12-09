package com.example.agreement.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.agreement.agreementdto.AgreementDto
import com.example.agreement.agreementdto.AddAgreementDto

class AddAgreementSharedViewModel: ViewModel() {
    private val sharedAddAgreementDto = MutableLiveData<AddAgreementDto>()
    private val getPreviewAgreementDto = MutableLiveData<AgreementDto>()

    fun sendAddAgreementDto(addAgreementDto: AddAgreementDto) {
        sharedAddAgreementDto.postValue(addAgreementDto)
    }

    fun getAddAgreementDto(): LiveData<AddAgreementDto> {
        return sharedAddAgreementDto
    }

    fun sendAgreementDto(addAgreementDto: AgreementDto) {
        getPreviewAgreementDto.postValue(addAgreementDto)
    }

    fun getAgreementDto(): LiveData<AgreementDto> {
        return getPreviewAgreementDto
    }
}


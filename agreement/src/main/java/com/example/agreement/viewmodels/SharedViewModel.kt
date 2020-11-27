package com.example.agreement.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val sharedPath = MutableLiveData<String>()

    fun sendPath(path: String) {
        sharedPath.postValue(path)
    }

    fun getPath(): LiveData<String> {
        return sharedPath
    }
}
package com.example.crs_ro.ui.clothes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClothesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Clothes Fragment"
    }
    val text: LiveData<String> = _text
}
package com.example.niedziala.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var _resText: MutableLiveData<String> = MutableLiveData("Your sensors will appear here")

    val resText: LiveData<String>
        get() = _resText
}
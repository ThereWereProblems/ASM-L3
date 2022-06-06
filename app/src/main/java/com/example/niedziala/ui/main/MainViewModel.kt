package com.example.niedziala.ui.main

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var _resText: MutableLiveData<String> = MutableLiveData("Your sensors will appear here")

    val resText: LiveData<String>
        get() = _resText

    lateinit var mSensorManager: SensorManager

    var sensors = mutableListOf<Sensor>()

    fun getSensors(){
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL)

        var resWord = ""

        for(sens in sensors){
            resWord += sens.name +"\n"
        }

        _resText = MutableLiveData(resWord)
    }

}
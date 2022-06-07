package com.example.niedziala.ui.main

import android.hardware.Sensor
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var _resText: MutableLiveData<String> = MutableLiveData("Your sensors will appear here")
    val resText: LiveData<String>
        get() = _resText

    var _resLight: MutableLiveData<String> = MutableLiveData("Sensors not exist")
    val resLight: LiveData<String>
        get() = _resLight

    var _resDistance: MutableLiveData<String> = MutableLiveData("Sensors not exist")
    val resDistance: LiveData<String>
        get() = _resDistance

    var _resAccelerometr: MutableLiveData<String> = MutableLiveData("Sensors not exist")
    val resAccelerometr: LiveData<String>
        get() = _resAccelerometr

    lateinit var mSensorManager: SensorManager
    lateinit var mLightSensor: Sensor
    lateinit var mProximitySensor: Sensor
    lateinit var mAccelerometrSensor: Sensor

    var sensors = mutableListOf<Sensor>()

    fun isSensorManagerInitialized() = ::mSensorManager.isInitialized
    fun isLightSensorInitialized() = ::mLightSensor.isInitialized
    fun isProximitySensorInitialized() = ::mProximitySensor.isInitialized
    fun isAccelerometrSensorInitialized() = ::mAccelerometrSensor.isInitialized


    fun getSensors(){
        sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL)

        var resWord = ""

        for(sens in sensors){
            resWord += sens.name +"\n"
        }

        _resText = MutableLiveData(resWord)
    }

    fun setLight(s: Float){
        Log.i("Light","im here")
        _resLight.value = s.toString()
    }

    fun setDistance(s: Float){
        _resDistance.value = s.toString()
    }

    fun setAccelerometr(s1: Float, s2: Float, s3: Float){
        _resAccelerometr.value = s1.toString() + " " + s2.toString() + " " + s3.toString()
    }
}
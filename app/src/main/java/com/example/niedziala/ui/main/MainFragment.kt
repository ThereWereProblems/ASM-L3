package com.example.niedziala.ui.main

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.niedziala.R
import com.example.niedziala.databinding.MainFragmentBinding

class MainFragment : Fragment(), SensorEventListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var thiscontext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.dane = viewModel
        binding.lifecycleOwner = this

        if (container != null) {
            thiscontext = container.context
        }

        if(!viewModel.isSensorManagerInitialized()){
            getManager()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.isLightSensorInitialized()){
            viewModel.mSensorManager.registerListener(this,viewModel.mLightSensor,SensorManager.SENSOR_DELAY_UI)
            Log.i("Light","init")
        }
        else{
            Log.i("Light","not init")
        }
        if (viewModel.isProximitySensorInitialized()){
            viewModel.mSensorManager.registerListener(this,viewModel.mProximitySensor,SensorManager.SENSOR_DELAY_UI)
            Log.i("Proximity","init")
        }
        else {
            Log.i("Proximity", "not init")
        }
        if (viewModel.isAccelerometrSensorInitialized()){
            viewModel.mSensorManager.registerListener(this,viewModel.mAccelerometrSensor,SensorManager.SENSOR_DELAY_UI)
            Log.i("Accelerometr","init")
        }
        else{
            Log.i("Accelerometr","not init")
        }

    }

    override fun onStop() {
        super.onStop()
        viewModel.mSensorManager.unregisterListener(this)
    }

    private fun getManager(){
        viewModel.mSensorManager = getActivity()?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        viewModel.getSensors()
        viewModel.mLightSensor = viewModel.mSensorManager.getDefaultSensor(5)
        viewModel.mProximitySensor = viewModel.mSensorManager.getDefaultSensor(8)
        viewModel.mAccelerometrSensor = viewModel.mSensorManager.getDefaultSensor(1)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        var sensorType = p0!!.sensor.type
        if(sensorType == Sensor.TYPE_LIGHT){
            var currVal = p0.values[0]
            viewModel.setLight(currVal)
            Log.i("Light",currVal.toString())
        }
        if(sensorType == Sensor.TYPE_PROXIMITY){
            var currVal = p0.values[0]
            viewModel.setDistance(currVal)
            Log.i("Proximity",currVal.toString())
        }
        if(sensorType == Sensor.TYPE_ACCELEROMETER){
            var currVal1 = p0.values[0]
            var currVal2 = p0.values[1]
            var currVal3 = p0.values[2]
            viewModel.setAccelerometr(currVal1, currVal2, currVal3)
            Log.i("Proximity",currVal1.toString() + " " + currVal2.toString() + " " + currVal3.toString())
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}
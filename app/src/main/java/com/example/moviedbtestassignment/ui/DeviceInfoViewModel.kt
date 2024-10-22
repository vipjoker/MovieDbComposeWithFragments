package com.example.moviedbtestassignment.ui

import androidx.lifecycle.ViewModel
import com.example.moviedbtestassignment.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DeviceInfoViewModel @Inject constructor(private val deviceInfoRepository: DeviceInfoRepository) : ViewModel() {


    fun deviceInfo() = deviceInfoRepository.getDeviceInfo().map {
        if (it is Response.Success) {
            it.data
        } else {
            DeviceInfo("", "", "", "")
        }
    }


}


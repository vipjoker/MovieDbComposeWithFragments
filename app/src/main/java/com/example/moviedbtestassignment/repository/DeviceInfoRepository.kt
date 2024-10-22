package com.example.moviedbtestassignment.repository

import kotlinx.coroutines.flow.Flow

interface DeviceInfoRepository {

    fun getDeviceInfo() : Flow<Response<DeviceInfo>>
}

data class DeviceInfo(
    val deviceName: String,
    val deviceBrand: String,
    val deviceModel: String,
    val softwareId: String
)
sealed class Response<out T> {
    data class Error(val error: String) : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
}
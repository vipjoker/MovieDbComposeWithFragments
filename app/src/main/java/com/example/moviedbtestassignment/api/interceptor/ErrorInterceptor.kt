package com.example.moviedbtestassignment.api.interceptor

import com.example.moviedbtestassignment.api.BasicResponse
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody


class ErrorInterceptor() : Interceptor {

    private val gson = Gson()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        return try {
            val response = chain.proceed(request)
           response
        } catch (e: Exception) {
            handleException(request, e)
        }
    }




    private fun handleException(request: Request, e: Exception): Response {
        val result = BasicResponse(e.message?:"Unknown error")
        val body  = gson.toJson(result).toResponseBody("application/json".toMediaTypeOrNull())
        val builder = Response.Builder()
        builder.request(request)
        builder.body(body)
        builder.code(200)
        builder.protocol(Protocol.HTTP_1_1)
        builder.message("")
        return builder.build()
    }

}
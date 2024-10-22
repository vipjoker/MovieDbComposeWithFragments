package com.realync.app.model.api.intereptor

import android.annotation.SuppressLint
import com.example.moviedbtestassignment.Constants
import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset


class AuthInterceptor( ) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

                val accessToken = Constants.API_KEY

                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $accessToken")
                        .build()
                    return chain.proceed(newRequest)

    }


}
package com.example.createyourfirstapp

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val request2 = request.newBuilder()
            .addHeader(API_AUTHORIZATION_HEADER, "16d9092a96msh041114fb792d0dbp1c0ef4jsncf4c23ea1c96")
            .build()
        return chain.proceed(request2)
    }

}

const val API_AUTHORIZATION_HEADER = "X-RapidAPI-Key"
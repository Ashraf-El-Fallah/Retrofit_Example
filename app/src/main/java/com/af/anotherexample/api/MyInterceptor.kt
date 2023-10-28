package com.af.anotherexample.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-type", "application/json")
            .addHeader("X-platform", "Android")
            .addHeader("X-Auth-token", "123456789")
            .build()
        return chain.proceed(request)
    }
}
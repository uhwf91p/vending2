package com.example.order.Server

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit1C {
    private val baseUrl="http://192.168.8.8/YUB_AF_PL/hs/"
    fun getRetrofit(): API {
        val retrofit1C =Retrofit.Builder()
            .baseUrl((baseUrl))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))//.addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClient(Interceptor1C()))
            .build()
        return retrofit1C.create(API::class.java)

    }
    private fun createOkHttpClient(interceptor:Interceptor):OkHttpClient{
        val httpClient=OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()

    }
    inner class Interceptor1C:Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed((chain.request()))
        }

    }


}
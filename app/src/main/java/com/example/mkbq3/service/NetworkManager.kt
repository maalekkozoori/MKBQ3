package com.example.mkbq3.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {
    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", "Bearer bkjcsbcg687hwgjhgksc")
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }.build()


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://papp.ir/api/v1/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val service = retrofit.create(CreateUsersApi::class.java)

}




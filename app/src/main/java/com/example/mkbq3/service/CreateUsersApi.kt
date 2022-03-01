package com.example.mkbq3.service

import android.graphics.Bitmap
import com.example.mkbq3.data.CreatUserBody
import com.example.mkbq3.data.ImageUpload
import com.example.mkbq3.data.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface CreateUsersApi {

    @POST("users")
    fun createUser(@Body user: CreatUserBody): Call<String>

    @GET("users")
    fun getUsersList(): Call<List<User>>

    @Multipart
    @POST("users/{userId}/image")
    fun uploadImage(@Part image : MultipartBody.Part, @Path("userId") userId: String): Call<Any>


    @POST("users/token")
    fun imageUrl(@Path("token") token:String, @Body image: Bitmap): Call<ImageUpload>

    @GET("users/{userId}")
    fun getUserInfo(@Path("userId") userId: String): Call<User>

}
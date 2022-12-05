package com.example.loginwithotp

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("auth/login_with_otp")
    @FormUrlEncoded
    fun login(@Field("email") email: String) : Call<CommomResponse>

    @POST("auth/verify_otp")
    @FormUrlEncoded
    fun verify(
        @Field("email") email: String,
        @Field("otp") otp: String
    ) : Call<LoginResponse>
}
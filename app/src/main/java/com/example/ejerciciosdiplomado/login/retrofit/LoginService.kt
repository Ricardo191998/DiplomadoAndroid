package com.example.ejerciciosdiplomado.login.retrofit

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type: application/json")
    @POST(Constants.API_PATH + Constants.AUTH + Constants.LOGIN_PATH)
    suspend fun loginUser(@Body data: LoginInfo): LoginResponse
}
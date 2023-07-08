package com.example.ejerciciosdiplomado.login.retrofit

import com.google.gson.annotations.SerializedName

class LoginInfo (
    @SerializedName(Constants.EMAIL_PARAM) val email: String,
    @SerializedName(Constants.PASSWORD_PARAM) val pass: String
)
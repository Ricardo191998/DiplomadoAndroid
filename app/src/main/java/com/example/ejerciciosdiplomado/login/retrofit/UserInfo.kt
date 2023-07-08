package com.example.ejerciciosdiplomado.login.retrofit

import com.google.gson.annotations.SerializedName

class UserInfo (
    @SerializedName(Constants.NAME_PARAM) val name: String,
    @SerializedName(Constants.EMAIL_PARAM) val email: String,
    @SerializedName(Constants.PASSWORD_PARAM) val pass: String
)
package com.example.ejerciciosdiplomado.login.retrofit

import java.io.Serializable

data class User(val uid: String,
                val email_address: String,
                val name: String,
                val profile_img: String) : Serializable


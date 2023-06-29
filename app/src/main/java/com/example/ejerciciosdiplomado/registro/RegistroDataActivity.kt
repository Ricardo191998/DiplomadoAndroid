package com.example.ejerciciosdiplomado.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ejerciciosdiplomado.R

class RegistroDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_data)

        getBundleData()
    }


    private fun getBundleData() {
        val bundleProfile = intent?.getBundleExtra( "EXTRA_BUNDLE_PROFILE")
        bundleProfile?. let {it
            val name = it.getString( "KEY_NAME")
            val surname = it.getString( "KEY_LASTNAME")
            val email = it.getString( "KEY_EMAIL")
            val gender = it.getString( "KEY_GENDER")

            val tvName = findViewById<TextView>(R.id.tvRegisterName)
            val tvLastName = findViewById<TextView>(R.id.tvRegisterLastName)
            val tvEmail = findViewById<TextView>(R.id.tvRegisterEmail)
            val tvGender = findViewById<TextView>(R.id.tvRegisterGender)

            tvName.text = name
            tvLastName.text=surname
            tvEmail.text= email
            tvGender.text = gender
        }
    }
}
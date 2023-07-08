package com.example.ejerciciosdiplomado.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.ejerciciosdiplomado.R
import com.example.ejerciciosdiplomado.databinding.ActivityAuthenticatedBinding
import com.example.ejerciciosdiplomado.login.retrofit.User
import com.squareup.picasso.Picasso

class AuthenticatedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthenticatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getSerializableExtra("EXTRA_USER", User::class.java)
        }else{
            intent.getSerializableExtra("EXTRA_USER") as User
        }

        setContentView(user)
        Log.e("T", user.toString())
    }

    private fun setContentView( user : User?) {
        Picasso.get()
            .load(user?.profile_img)
            .error(R.drawable.ic_people)
            .placeholder(R.drawable.img_logo)
            .into(binding.ivProfile)

        binding.tvRegisterName.text = user?.name
        binding.tvRegisterEmail.text = user?.email_address
    }
}
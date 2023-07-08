package com.example.ejerciciosdiplomado.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ejerciciosdiplomado.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragmentManager: FragmentManager = supportFragmentManager

        val fragment: Fragment = LoginFragment()

        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.fragment_login, fragment)

        transaction.commit()
    }
}
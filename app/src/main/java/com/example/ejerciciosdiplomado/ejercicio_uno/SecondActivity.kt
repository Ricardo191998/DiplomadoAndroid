package com.example.ejerciciosdiplomado.ejercicio_uno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.ejerciciosdiplomado.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val info = intent?.getStringExtra(  "KEY_INFO")
        val bundleProfile = intent?.getBundleExtra( "EXTRA_BUNDLE_PROFILE")
        bundleProfile?. let {it
            val name = it.getString( "KEY_NAME")
            val surname = it.getString( "KEY_SURNAME")
            val age = it.getInt( "KEY _AGE")

            val textName = findViewById<TextView>(R.id.tvName)
            val textSurname = findViewById<TextView>(R.id.tvLastName)
            val textAge = findViewById<TextView>(R.id.tvAge)

            textName.text = name
            textSurname.text=surname
            textAge.text= "${age}"
        }
        close()
    }

    private fun close(){
        val btnReturn = findViewById<Button>(R.id.btnclose)

        btnReturn.setOnClickListener{
            val intent = Intent().apply {
                putExtra("EXTRA_STRING_RESULT", "String regresada")
                putExtra("EXTRA_INT_RESULT", 10)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }


}
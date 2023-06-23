package com.example.ejerciciosdiplomado.ejercicio_uno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ejerciciosdiplomado.R

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        setupCall()
        setupCallWithResult()
    }

    private fun setupCall() {
        val btnCall = findViewById<Button>(R.id.btnCall)

        btnCall.setOnClickListener{

            val bundle = Bundle().apply {
                putString("KEY_NAME", "Ricardo")
                putString("KEY_SURNAME", "Rosales")
                putInt("KEY_AGE", 25)
            }

            val secondIntent = Intent(this, SecondActivity::class.java).apply {
                putExtra("KEY_INFO", "Info enviada al activity 2")
                putExtra("EXTRA_BUNDLE_PROFILE", bundle)
            }
            startActivity(secondIntent)
        }
    }

    private val responseLauncher = registerForActivityResult (ActivityResultContracts.StartActivityForResult()){ result  ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "ResultCode: ${result.resultCode} - OK", Toast.LENGTH_SHORT).show()
            val resultString = result.data?.getStringExtra("EXTRA_STRING_RESULT")

            Toast.makeText(this, resultString, Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this, "ResultCode: ${result.resultCode} - CANCELLED", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupCallWithResult() {
        val btnResult = findViewById<Button>(R.id.btnResult)

        btnResult.setOnClickListener{

            val intent = Intent(this, SecondActivity::class.java)
            responseLauncher.launch(intent)
        }
    }
}
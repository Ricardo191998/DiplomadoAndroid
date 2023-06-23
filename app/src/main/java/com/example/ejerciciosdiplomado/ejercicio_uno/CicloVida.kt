package com.example.ejerciciosdiplomado.ejercicio_uno

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ejerciciosdiplomado.R

class CicloVida : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "CICLO DE VIDA; on start", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "CICLO DE VIDA; on onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "CICLO DE VIDA; on onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "CICLO DE VIDA; on onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "CICLO DE VIDA; on Destroy", Toast.LENGTH_SHORT).show()
    }
}
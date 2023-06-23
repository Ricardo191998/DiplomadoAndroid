package com.example.ejerciciosdiplomado.ejercicio_uno

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ejerciciosdiplomado.R

class IntentImplicitoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_implicito)

        val button = findViewById<Button>(R.id.btnIntent)
        val btnUrl = findViewById<Button>(R.id.btnUrl)

        button.setOnClickListener{
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_SUBJECT, "Aviso Urgente")
                putExtra(Intent.EXTRA_TEXT, "Esto es una prueba para enviar correo mediante un intent implicito")
            }

            startActivity(emailIntent)
        }

        btnUrl.setOnClickListener{
            val url = "https://www.google.com"
            val intent =  Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}
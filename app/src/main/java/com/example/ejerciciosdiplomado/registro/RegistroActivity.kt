package com.example.ejerciciosdiplomado.registro

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import com.example.ejerciciosdiplomado.R

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        setupSpinner()
        checkTerms()
        onClickRegister()
    }

    private fun checkTerms () {
       val terms=  findViewById<RadioButton>(R.id.cbTerms)
        val btnRegister = findViewById<Button>(R.id.btRegister)

        terms.setOnCheckedChangeListener { view, isChecked ->
            btnRegister.isEnabled = isChecked
        }
    }

    private fun onClickRegister(){
        val btnRegister = findViewById<Button>(R.id.btRegister)

        btnRegister.setOnClickListener {
            val tvName = findViewById<EditText>(R.id.etName)
            val tvLastName = findViewById<EditText>(R.id.etLastName)
            val tvEmail = findViewById<EditText>(R.id.etEmail)
            val tvPassword = findViewById<EditText>(R.id.etPassword)

            val spinner = findViewById<Spinner>(R.id.spRegister)

            val validationName = validateText(tvName, "Nombre requerido", false)
            val validationLastName = validateText(tvLastName, "Apellido requerido", false)
            val validationEmail = validateText(tvEmail, "Email requerido", true)
            val validationPassword = validateText(tvPassword, "Contraseña requerida", false)

            if( validationName && validationLastName && validationEmail && validationPassword){
                val bundle = Bundle().apply {
                    putString("KEY_NAME", tvName.text.toString().trim())
                    putString("KEY_LASTNAME", tvLastName.text.toString().trim())
                    putString("KEY_EMAIL", tvEmail.text.toString().trim())
                    putString("KEY_PASSWORD", tvPassword.text.toString().trim())
                    putString("KEY_GENDER", spinner.selectedItem.toString())
                }

                val secondIntent = Intent(this, RegistroDataActivity::class.java).apply {
                    putExtra("EXTRA_BUNDLE_PROFILE", bundle)
                }
                startActivity(secondIntent)
            }
        }
    }

    private fun validateText(textView: EditText, message: String, isEmail: Boolean): Boolean{
        val inputText = textView.text.toString().trim()
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") // Expresión regular para validar el formato del correo electrónico

        if (inputText.isEmpty()) {
            textView.error = message
            return false
        } else if(isEmail && !inputText.matches(emailPattern)){
            textView.error = message
            return false
        }else {
            textView.error = null
            textView.setTextColor(Color.BLACK)
            return true
        }

    }

    private fun setupSpinner() {

        val context = this
        val items = arrayOf("Mujer", "Hombre")
        val spinner = findViewById<Spinner>(R.id.spRegister)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(context, "Seleccionado: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Se llama cuando no se selecciona ningún elemento del Spinner
            }
        })
    }

}
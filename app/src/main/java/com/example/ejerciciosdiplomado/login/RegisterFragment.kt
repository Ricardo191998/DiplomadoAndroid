package com.example.ejerciciosdiplomado.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.ejerciciosdiplomado.R

import com.example.ejerciciosdiplomado.databinding.FragmentRegisterBinding
import com.example.ejerciciosdiplomado.login.retrofit.Constants
import com.example.ejerciciosdiplomado.login.retrofit.LoginInfo
import com.example.ejerciciosdiplomado.login.retrofit.UserInfo
import com.example.ejerciciosdiplomado.login.retrofit.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding : FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        setUpRegister()
        checkTerms()
    }

    private fun checkTerms () {
        val terms =  binding.cbTerms
        val btnRegister = binding.btRegister

        terms.setOnCheckedChangeListener { view, isChecked ->
            btnRegister.isEnabled = isChecked
        }
    }

    private fun setUpRegister() {
        val btnRegister = binding.btRegister

        btnRegister.setOnClickListener {

            val etName = binding.etName
            val etEmail = binding.etEmail
            val etPassword = binding.etPassword

            val validationName = validateText(etName, "Nombre requerido", false)
            val validationEmail = validateText(etEmail, "Email requerido", true)
            val validationPassword = validateText(etPassword, "Contraseña requerida", false)

            if(validationName && validationEmail && validationPassword) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val service = retrofit.create(UserService::class.java)
                register(etName.text.toString().trim(), etEmail.text.toString().trim(), etPassword.text.toString().trim(), service)
            }

        }
    }

    private fun register(name: String, email: String, password: String, service: UserService) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = service.createUser(UserInfo(name, email, password))
                Log.e("T", result.usuario.toString())
                val context: Context = requireContext()
                val intent = Intent(context, AuthenticatedActivity::class.java).apply {
                    putExtra("EXTRA_USER", result.usuario)
                }
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("E", "Cayo en algun error al crear al usuario")
                Log.e("E", e.toString())
                (e as? HttpException)?.let { checkError(e) }
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

    private suspend fun checkError(e: HttpException)= when(e.code()){
        400 -> {
            val context: Context = requireContext()
            Log.e("E", getString(R.string.main_error_server))
            // Toast.makeText(context, getString(R.string.main_error_server), Toast.LENGTH_SHORT).show()
        }
        else -> {
            val context: Context = requireContext()
            Log.e("E", getString(R.string.main_error_server))
            //Toast.makeText(context, getString(R.string.main_error_server), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
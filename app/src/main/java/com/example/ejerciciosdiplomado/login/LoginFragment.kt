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
import com.example.ejerciciosdiplomado.R
import com.example.ejerciciosdiplomado.databinding.FragmentLoginBinding
import com.example.ejerciciosdiplomado.login.retrofit.Constants
import com.example.ejerciciosdiplomado.login.retrofit.LoginInfo
import com.example.ejerciciosdiplomado.login.retrofit.LoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnRegister = binding.btnLoginRegister

        btnRegister.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager

            val fragmentB = RegisterFragment()

            val transaction = fragmentManager.beginTransaction()

            transaction.replace(R.id.fragment_login, fragmentB)

            transaction.addToBackStack(null)

            transaction.commit()
        }

        setupLogin()

        return root
    }

    private fun setupLogin() {
        val btnLogin = binding.btLogin

        btnLogin.setOnClickListener {
            val tvEmail = binding.etEmailLogin
            val tvPassword = binding.etLoginPassword
            val validationEmail = validateText(tvEmail, "Email requerido", true)
            val validationPassword = validateText(tvPassword, "Contraseña requerida", false)

            if(validationEmail && validationPassword) {

                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val service = retrofit.create(LoginService::class.java)
                login(tvEmail.text.toString().trim(), tvPassword.text.toString().trim(), service)
            }
        }
    }

    private fun login(email: String, password: String, service: LoginService) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result = service.loginUser(LoginInfo(email, password))
                //Log.e("T", result.token.toString())
                val context: Context = requireContext()
                val intent = Intent(context, AuthenticatedActivity::class.java).apply {
                    putExtra("EXTRA_USER", result.usuario)
                }
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("E", "Cayo en algun error")
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
        fun newInstance() = LoginFragment()
    }
}
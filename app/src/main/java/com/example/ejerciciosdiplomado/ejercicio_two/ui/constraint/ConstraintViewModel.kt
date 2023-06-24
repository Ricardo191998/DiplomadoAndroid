package com.example.ejerciciosdiplomado.ejercicio_two.ui.constraint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConstraintViewModel : ViewModel(){
    private val _text = MutableLiveData<String>().apply {
        value = "This is a Contraint Layout"
    }
    val text: LiveData<String> = _text
}
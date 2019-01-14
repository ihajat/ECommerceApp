package com.example.ecommerceapp.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecommerceapp.repository.WorldPayRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentViewModel::class.java)) {
            val repo = WorldPayRepository()
            @Suppress("UNCHECKED_CAST")
            return PaymentViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
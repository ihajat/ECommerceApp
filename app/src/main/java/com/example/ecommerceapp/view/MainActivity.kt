package com.example.ecommerceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.presenter.Presenter
import com.example.ecommerceapp.viewmodel.PaymentViewModel
import com.example.ecommerceapp.viewmodel.ViewModelFactory
import com.example.ecommerceapp.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var activityMainBinding: ActivityMainBinding
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PaymentViewModel::class.java)
        viewModel.getPaymentResult().observe(this, Observer {
            onSuccess(it)
        })

        activityMainBinding!!.presenter = object: Presenter {
            override fun authorisePayment() {
                var amount = activityMainBinding.amountEdt.text.toString()
                var type = activityMainBinding.edtType.text.toString()
                var desc = activityMainBinding.edtDescription.text.toString()

                viewModel.authorisePayment(amount,type,desc)
            }
        }

    }


    private fun onSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}




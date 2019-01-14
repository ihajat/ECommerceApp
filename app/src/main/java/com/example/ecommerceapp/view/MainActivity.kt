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

        //DataBinding
        var activityMainBinding: ActivityMainBinding
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //Retrieve our lifecycler-aware ViewModel using a factory.
        val viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PaymentViewModel::class.java)

        //Observe on our MutableLiveData for changes
        viewModel.getPaymentResult().observe(this, Observer {
            onChange(it)
        })

        //Our controller, reprsenter by a presenter that directs flow
        activityMainBinding!!.presenter = object: Presenter {

            //refund payments
            override fun refundPayment() {
                var order_code = activityMainBinding.edtOrderCode.text.toString()

                viewModel.refundPayment(order_code)
            }

            //cancel payments
            override fun cancelPayment() {
                var order_code = activityMainBinding.edtOrderCode.text.toString()

                viewModel.cancelPayment(order_code)
            }

            //authorise payments
            override fun authorisePayment() {
                var amount = activityMainBinding.amountEdt.text.toString()
                var type = activityMainBinding.edtType.text.toString()
                var desc = activityMainBinding.edtDescription.text.toString()

                viewModel.authorisePayment(amount,type,desc)
            }
        }
    }

    //Is called from the MutableLiveData changes
    private fun onChange(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}




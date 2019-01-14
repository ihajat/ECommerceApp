package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.repository.Repository
import com.worldpay.gateway.clearwater.client.core.dto.request.OrderRequest

class PaymentViewModel(val repository: Repository) : ViewModel(){

    fun orderRequestFactory(orderAmount: String, orderType: String, description: String): OrderRequest {
        return OrderRequest()
    }
    fun authorisePayment(orderAmount: String, orderType: String, description: String){
    }

    fun getPaymentResult(): String {
        return ""
    }

}
package com.example.ecommerceapp.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.repository.Repository
import com.worldpay.gateway.clearwater.client.core.dto.request.OrderRequest
import com.worldpay.gateway.clearwater.client.core.dto.CountryCode
import com.worldpay.gateway.clearwater.client.core.dto.CurrencyCode
import com.worldpay.gateway.clearwater.client.core.dto.common.Address

class PaymentViewModel(val repository: Repository) : ViewModel(){

    var amount = ObservableField("")
    var type = ObservableField("")
    var description = ObservableField("")
    var resultData = MutableLiveData<String>()

    fun getPaymentResult(): MutableLiveData<String> {
        return resultData
    }

    fun orderRequestFactory(orderAmount: String, orderType: String, description: String): OrderRequest {
        val orderRequest = OrderRequest()
        orderRequest.orderType = orderType
        orderRequest.isAuthorizeOnly = true
        orderRequest.token = repository.getToken() //  "your-order-token"
        orderRequest.amount = orderAmount.toInt()
        orderRequest.currencyCode = CurrencyCode.GBP
        orderRequest.name = "test name"
        orderRequest.orderDescription = description
        orderRequest.customerOrderCode = "123"

        val address = Address()
        address.setAddress1("address1")
        address.setCity("city")
        address.setCountryCode(CountryCode.GB)
        address.setPostalCode("postCode")
        orderRequest.billingAddress = address

        return orderRequest
    }
    fun authorisePayment(orderAmount: String, orderType: String, description: String){

        this.amount.set(orderAmount)
        this.type.set(orderType)
        this.description.set(description)

        val orderRequest = orderRequestFactory(orderAmount,orderType,description)

        val result = repository.authorisePayment(orderRequest)

        resultData.value = result

    }


}
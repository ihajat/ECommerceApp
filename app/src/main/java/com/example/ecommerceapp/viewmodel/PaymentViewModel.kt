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
    var orderCode = ObservableField("")
    var resultPaymentData = MutableLiveData<String>()
    var resultCancelData = MutableLiveData<String>()

    fun getPaymentResult(): MutableLiveData<String> {
        return resultPaymentData
    }

    fun getCancelResult(): MutableLiveData<String> {
        return resultCancelData
    }

    /*
        The WorldPay APIs order ( standard ) requires an order request

        The following fields are recommended to improve risk checking:

            name
            billingAddress
            deliveryAddress
            shopperEmailAddress
            shopperIpAddress
            shopperSessionId
            Other optional fields include:

            customerOrderCode: code under which this order is known in your systems
            settlementCurrency: the currency you want to be paid in

     */
    fun orderRequestFactory(orderAmount: String, orderType: String, description: String): OrderRequest {
        val orderRequest = OrderRequest()
        orderRequest.orderType = orderType
        orderRequest.isAuthorizeOnly = true
        orderRequest.token = repository.getToken() //  "your-order-token"
        orderRequest.amount = validateAmount(orderAmount)
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

    fun validateAmount(orderAmount: String): Int {
        if(orderAmount == null || orderAmount.equals("")) return 0
        return orderAmount.toInt()
    }

    /*
        allows a shopper to Authorize and optionally Cancel a payment
        using the Access Worldpay API
        Currently ALL API calls have been replaced by stubs / fake data

        Creating a new order is achieved through a POST on the Order API.
        A new order can be created using a token, which represents the customer's card details
        or chosen payment method stored on our servers, added to your checkout form by the WorldPay.js
        library or obtained via the Token API.

        For more details of the WorldPay authorisePayment API:
        https://developer.worldpay.com/jsonapi/api#creatingorder

     */
    fun authorisePayment(orderAmount: String, orderType: String, description: String){

        this.amount.set(orderAmount)
        this.type.set(orderType)
        this.description.set(description)

        val orderRequest = orderRequestFactory(orderAmount,orderType,description)

        val result = repository.authorisePayment(orderRequest)

        resultPaymentData.value = result

    }

    fun cancelPayment(order_code: String) {

        this.orderCode.set(order_code)

        val result = repository.cancelPayment(order_code)

        resultCancelData.value = result
    }


}
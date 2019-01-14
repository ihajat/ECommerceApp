package com.example.ecommerceapp.repository


import com.worldpay.gateway.clearwater.client.core.dto.request.OrderRequest
import com.worldpay.gateway.clearwater.client.core.exception.WorldpayException


class WorldPayRepository:  Repository {
    override fun authorisePayment(orderRequest: OrderRequest): String{

        try {

//        val restClient = WorldpayRestClient(BuildConfig.SERVICE_KEY)
//        restClient.orderService.create(orderRequest)

            return "success"

        } catch (e: WorldpayException) {
            println("Http Status code: " + e.apiError.httpStatusCode)
            println("Error code: " + e.apiError.customCode)
            println("Error description: " + e.apiError.description)
            println("Error message: " + e.apiError.message)

            return "fail"
        }
    }

    override fun getToken(): String{

//        val restClient = WorldpayRestClient(BuildConfig.SERVICE_KEY)
//        val tokenResponse = restClient.getTokenService().get("your-reusable-token")
//        return tokenResponse.token

        return "dummy token"
    }

}
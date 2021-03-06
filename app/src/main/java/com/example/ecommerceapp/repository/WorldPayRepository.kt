package com.example.ecommerceapp.repository


import com.worldpay.gateway.clearwater.client.core.dto.CountryCode
import com.worldpay.gateway.clearwater.client.core.dto.CurrencyCode
import com.worldpay.gateway.clearwater.client.core.dto.common.Address
import com.worldpay.gateway.clearwater.client.core.dto.request.OrderRequest
import com.worldpay.gateway.clearwater.client.core.exception.WorldpayException
import com.worldpay.sdk.WorldpayRestClient
import com.worldpay.sdk.OrderService




class WorldPayRepository:  Repository {
    /*

        Authorized orders can be refunded by sending a refund request.

        For more details of the WorldPay refund Payment API:
        https://developer.worldpay.com/jsonapi/api#refundingorder

     */
    override fun refundPayment(order_code: String): String {

        try {

//            val restClient = WorldpayRestClient("your-test-service-key")
//
//            restClient.orderService.refund('your-order-code')


            return "success"

        } catch (e: WorldpayException) {
            println("Http Status code: " + e.apiError.httpStatusCode)
            println("Error code: " + e.apiError.customCode)
            println("Error description: " + e.apiError.description)
            println("Error message: " + e.apiError.message)

            return "fail"
        }

    }


    /*

        Authorized orders can be cancelled by sending a cancel request.

        For more details of the WorldPay cancel Payment API:
        https://developer.worldpay.com/jsonapi/api#cancelorder

     */
    override fun cancelPayment(order_code: String): String {

        try {

//            val orderService = WorldpayRestClient('your-service-key').orderService
//            orderService.cancel("your-order-code")

            return "success"

        } catch (e: WorldpayException) {
            println("Http Status code: " + e.apiError.httpStatusCode)
            println("Error code: " + e.apiError.customCode)
            println("Error description: " + e.apiError.description)
            println("Error message: " + e.apiError.message)

            return "fail"
        }

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

    /*

        Create a new token through a POST on the Tokens API,
        specifying paymentMethod's type as Card

        For more details of the WorldPay tokens API:
        https://developer.worldpay.com/jsonapi/api#creatingtoken
     */
    override fun getToken(): String{

//        val restClient = WorldpayRestClient(BuildConfig.SERVICE_KEY)
//        val tokenResponse = restClient.getTokenService().get("your-reusable-token")
//        return tokenResponse.token

        return "dummy token"
    }
}
package com.example.ecommerceapp.repository

import com.worldpay.gateway.clearwater.client.core.dto.request.OrderRequest

interface Repository {
    fun authorisePayment(orderRequest: OrderRequest): String
    fun getToken(): String
}
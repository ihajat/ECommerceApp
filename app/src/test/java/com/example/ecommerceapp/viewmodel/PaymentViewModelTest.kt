package com.example.ecommerceapp.viewmodel

import com.example.ecommerceapp.repository.Repository
import com.worldpay.gateway.clearwater.client.core.dto.request.OrderRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PaymentViewModelTest {



    lateinit var viewModel: PaymentViewModel

    @Mock
    lateinit var repository: Repository


    @Before
    fun setUp() {
        viewModel = PaymentViewModel(repository)
    }

    @After
    fun tearDown() {
    }

    //Scenario 1, when authorisation of Payment is successful return/display success
    @Test
    fun displaySuccessWhenAuthoriseOfPaymentSuccessful() {

        var orderRequest = OrderRequest()

        //When
        Mockito.`when`(repository.authorisePayment(orderRequest)).thenReturn("success")

        //Then
        viewModel.authorisePayment("","","")

        //Verify
        assertEquals("success",viewModel.getPaymentResult())

    }


}
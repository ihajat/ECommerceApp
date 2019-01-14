package com.example.ecommerceapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ecommerceapp.repository.Repository
import com.worldpay.gateway.clearwater.client.core.dto.request.OrderRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PaymentViewModelTest {

    private val invalid_order_response = "fail"
    private val valid_order_response = "success"
    private val valid_order_amount = "100"
    private val valid_order_type = "SALE"
    private val order_description = "test data"

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: PaymentViewModel

    lateinit var orderRequest: OrderRequest

    @Mock
    lateinit var repository: Repository


    @Before
    fun setUp() {
        viewModel = PaymentViewModel(repository)
        orderRequest = viewModel.orderRequestFactory(valid_order_amount,valid_order_type,order_description)
    }

    @After
    fun tearDown() {
    }

    // Scenario 1, when authorisation of Payment is successful return/display success
    // Precondition: requires valid input
    @Test
    fun displaySuccessWhenAuthoriseOfPaymentSuccessful() {

        //When
        Mockito.`when`(repository.authorisePayment(orderRequest)).thenReturn(valid_order_response)

        //Then
        viewModel.authorisePayment(valid_order_amount,valid_order_type,order_description)

        //Verify
        assertEquals(valid_order_response,viewModel.getPaymentResult().value)

    }


}
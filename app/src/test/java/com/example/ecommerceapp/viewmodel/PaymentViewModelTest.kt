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
    private val invalid_order_amount = ""
    private val valid_order_type = "SALE"
    private val order_description = "test data"
    private val order_code = "order code"
    private val successful_cancel_response = "success"
    private val unsuccessful_cancel_response = "fail"
    private val successful_refund_response = "success"
    private val unsuccessful_refund_response = "fail"
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: PaymentViewModel

    lateinit var orderRequest: OrderRequest

    @Mock
    lateinit var repository: Repository


    @Before
    fun setUp() {
        viewModel = PaymentViewModel(repository)
    }

    @After
    fun tearDown() {
    }

    // Scenario 1, when authorisation of Payment is successful return/display success
    // Precondition: requires valid input
    @Test
    fun displaySuccessWhenAuthoriseOfPaymentSuccessful() {

        orderRequest = viewModel.orderRequestFactory(valid_order_amount,valid_order_type,order_description)

        //When
        Mockito.`when`(repository.authorisePayment(orderRequest)).thenReturn(valid_order_response)

        //Then
        viewModel.authorisePayment(valid_order_amount,valid_order_type,order_description)

        //Verify
        assertEquals(valid_order_response,viewModel.getPaymentResult().value)

    }

    //Scenario 2, when authorisation of Payment is unsuccessful return/display fail
    // Precondition: requires invalid amount
    @Test
    fun displayFailWhenAuthoriseOfPaymentUnsuccessful() {

        orderRequest = viewModel.orderRequestFactory(invalid_order_amount,valid_order_type,order_description)

        //When
        Mockito.`when`(repository.authorisePayment(orderRequest)).thenReturn(invalid_order_response)

        //Then
        viewModel.authorisePayment(invalid_order_amount,valid_order_type,order_description)

        //Verify
        assertEquals(invalid_order_response,viewModel.getPaymentResult().value)
    }

    // Scenario 3, cancel a Payment is successful return/display success
    // Precondition: requires valid input
    @Test
    fun displaySuccessWhenCancellationOfPayment() {

        //When
        Mockito.`when`(repository.cancelPayment(order_code)).thenReturn(successful_cancel_response)

        //Then
        viewModel.cancelPayment(order_code)

        //Verify
        assertEquals(successful_cancel_response,viewModel.getCancelResult().value)

    }

    // Scenario 4, cancel a Payment is unsuccessful return/display fail
    // Precondition: na
    @Test
    fun displayFailWhenCancellationOfPayment() {

        //When
        Mockito.`when`(repository.cancelPayment(order_code)).thenReturn(unsuccessful_cancel_response)

        //Then
        viewModel.cancelPayment(order_code)

        //Verify
        assertEquals(unsuccessful_cancel_response,viewModel.getCancelResult().value)

    }

    // Scenario 5, refund a Payment is successful return/display success
    // Precondition: requires valid input
    @Test
    fun displaySuccessWhenRefundingOfPayment() {

        //When
        Mockito.`when`(repository.refundPayment(order_code)).thenReturn(successful_refund_response)

        //Then
        viewModel.refundPayment(order_code)

        //Verify
        assertEquals(successful_refund_response,viewModel.getRefundResult().value)

    }
}
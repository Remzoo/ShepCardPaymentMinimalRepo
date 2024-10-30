package com.example.shepcardpaymentminimalrepo

import java.io.Serializable

data class PaymentResult(
    val isSuccess: Boolean,
    val errorCode: Int? = null,
    val errorDescription: CharSequence? = null
) : Serializable

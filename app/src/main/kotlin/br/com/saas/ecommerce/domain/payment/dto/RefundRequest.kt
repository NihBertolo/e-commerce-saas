package br.com.saas.ecommerce.domain.payment.dto

import java.util.*
import java.math.BigDecimal


data class RefundRequest(
    val paymentId: UUID,
    val amount: BigDecimal,
    val reason: String? = null
)
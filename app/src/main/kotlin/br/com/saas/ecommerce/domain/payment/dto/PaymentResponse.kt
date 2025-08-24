package br.com.saas.ecommerce.domain.payment.dto

import br.com.saas.ecommerce.domain.payment.model.PaymentStatus
import java.util.*

data class PaymentResponse(
    val paymentId: UUID,
    val status: PaymentStatus,
    val providerTransactionId: String?,
    val rawResponse: String? = null
)
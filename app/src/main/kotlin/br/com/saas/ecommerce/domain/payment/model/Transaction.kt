package br.com.saas.ecommerce.domain.payment.model

import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val gatewayTransactionId: String?,
    val amount: BigDecimal,
    val createdAt: Instant = Instant.now(),
    val status: PaymentStatus
)
package br.com.saas.ecommerce.domain.payment.model

import java.math.BigDecimal
import java.util.*

data class PaymentSplit(
    val recipientId: UUID, // lojista (tenant) ou plataforma
    val amount: BigDecimal,
    val isPlatformFee: Boolean = false
)
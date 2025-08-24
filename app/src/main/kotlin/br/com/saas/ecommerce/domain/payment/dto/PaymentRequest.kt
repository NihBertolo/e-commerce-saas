package br.com.saas.ecommerce.domain.payment.dto

import br.com.saas.ecommerce.domain.payment.model.PaymentMethod
import br.com.saas.ecommerce.domain.payment.model.PaymentSplit
import java.math.BigDecimal
import java.util.*

data class PaymentRequest(
    val orderId: UUID,
    val tenantId: UUID,
    val amount: BigDecimal,
    val currency: String = "BRL",
    val method: PaymentMethod,
    val splits: List<PaymentSplit>
)
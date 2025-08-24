package br.com.saas.ecommerce.domain.order.model

import java.math.BigDecimal
import java.util.*

data class OrderItem(
    val id: UUID = UUID.randomUUID(),
    val productId: UUID,
    val sku: String,
    val name: String,
    val quantity: Int,
    val price: BigDecimal
) {
    val total: BigDecimal
        get() = price.multiply(BigDecimal.valueOf(quantity.toLong()))
}
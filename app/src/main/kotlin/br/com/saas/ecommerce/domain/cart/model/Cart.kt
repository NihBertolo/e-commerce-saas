package br.com.saas.ecommerce.domain.cart.model

import br.com.saas.ecommerce.tenant.TenantAwareEntity
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class Cart(
    val id: UUID = UUID.randomUUID(),
    override val tenantId: String,
    val userId: String,
    val items: MutableList<CartItem> = mutableListOf(),
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now()
) : TenantAwareEntity {

    fun addItem(item: CartItem) {
        val existing = items.find { it.productId == item.productId }
        if (existing != null) {
            existing.quantity += item.quantity
        } else {
            items.add(item)
        }
        updatedAt = Instant.now()
    }

    fun removeItem(productId: String) {
        items.removeIf { it.productId == productId }
        updatedAt = Instant.now()
    }

    fun clear() {
        items.clear()
        updatedAt = Instant.now()
    }

    fun total(): BigDecimal = items.sumOf { it.price.multiply(BigDecimal.valueOf(it.quantity.toLong())) }
}
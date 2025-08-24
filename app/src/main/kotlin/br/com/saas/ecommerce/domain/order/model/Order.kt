package br.com.saas.ecommerce.domain.order.model

import br.com.saas.ecommerce.tenant.TenantAwareEntity
import java.time.Instant
import java.util.*

data class Order(
    val id: UUID = UUID.randomUUID(),
    override val tenantId: String,
    val userId: UUID,
    val items: MutableList<OrderItem> = mutableListOf(),
    var status: OrderStatus = OrderStatus.CREATED,
    var paymentMethod: PaymentMethod? = null,
    var totalAmount: Double = 0.0,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now()
) : TenantAwareEntity {

    fun calculateTotal() {
        totalAmount = items.sumOf { it.total.toDouble() }
        updatedAt = Instant.now()
    }

    fun addItem(item: OrderItem) {
        items.add(item)
        calculateTotal()
    }

    fun removeItem(itemId: UUID) {
        items.removeIf { it.id == itemId }
        calculateTotal()
    }

    fun confirmPayment(method: PaymentMethod) {
        this.paymentMethod = method
        this.status = OrderStatus.CONFIRMED
        this.updatedAt = Instant.now()
    }

    fun changeStatus(newStatus: OrderStatus) {
        this.status = newStatus
        this.updatedAt = Instant.now()
    }
}
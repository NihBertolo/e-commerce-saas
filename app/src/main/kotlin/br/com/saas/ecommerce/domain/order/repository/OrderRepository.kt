package br.com.saas.ecommerce.domain.order.repository

import br.com.saas.ecommerce.domain.order.model.Order
import java.util.*

interface OrderRepository {
    fun save(order: Order): Order
    fun findByIdAndTenant(id: UUID, tenantId: String): Order?
    fun findAllByTenant(tenantId: String): List<Order>
    fun findByUserIdAndTenant(userId: UUID, tenantId: String): List<Order>
    fun delete(order: Order)
}
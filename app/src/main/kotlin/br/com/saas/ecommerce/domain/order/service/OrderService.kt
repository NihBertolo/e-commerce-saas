package br.com.saas.ecommerce.domain.order.service

import br.com.saas.ecommerce.domain.order.model.Order
import br.com.saas.ecommerce.domain.order.model.OrderItem
import br.com.saas.ecommerce.domain.order.model.OrderStatus
import br.com.saas.ecommerce.domain.order.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun createOrder(order: Order): Order {
        order.calculateTotal()
        return orderRepository.save(order)
    }

    fun addItemToOrder(orderId: UUID, tenantId: String, item: OrderItem): Order {
        val order = getOrder(orderId, tenantId)
        order.addItem(item)
        return orderRepository.save(order)
    }

    fun confirmPayment(orderId: UUID, tenantId: String, method: PaymentMethod): Order {
        val order = getOrder(orderId, tenantId)
        order.confirmPayment(method)
        return orderRepository.save(order)
    }

    fun updateOrderStatus(orderId: UUID, tenantId: String, status: OrderStatus): Order {
        val order = getOrder(orderId, tenantId)
        order.changeStatus(status)
        return orderRepository.save(order)
    }

    fun getOrder(orderId: UUID, tenantId: String): Order {
        return orderRepository.findByIdAndTenant(orderId, tenantId)
            ?: throw IllegalArgumentException("Pedido não encontrado")
    }

    fun listOrders(tenantId: String): List<Order> {
        return orderRepository.findAllByTenant(tenantId)
    }
}
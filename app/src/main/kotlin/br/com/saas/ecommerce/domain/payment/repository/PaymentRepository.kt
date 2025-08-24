package br.com.saas.ecommerce.domain.payment.repository

import br.com.saas.ecommerce.domain.payment.model.Payment
import java.util.*

interface PaymentRepository {
    fun save(payment: Payment): Payment
    fun findByIdAndTenant(id: UUID, tenantId: String): Payment?
    fun findByOrderIdAndTenant(orderId: UUID, tenantId: String): Payment?
    fun findAllByTenant(tenantId: String): List<Payment>
}
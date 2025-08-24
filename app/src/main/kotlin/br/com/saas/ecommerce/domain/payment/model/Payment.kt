package br.com.saas.ecommerce.domain.payment.model

import br.com.saas.ecommerce.tenant.TenantAwareEntity
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class Payment(
    val id: UUID = UUID.randomUUID(),
    override val tenantId: String,
    val orderId: UUID,
    val userId: UUID,
    val gateway: PaymentGateway,
    var status: PaymentStatus = PaymentStatus.PENDING,
    var amount: BigDecimal,
    val transactions: MutableList<Transaction> = mutableListOf(),
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now()
) : TenantAwareEntity {

    fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
        status = transaction.status
        updatedAt = Instant.now()
    }

    fun markAsSuccess(gatewayTransactionId: String) {
        addTransaction(Transaction(
            gatewayTransactionId = gatewayTransactionId,
            amount = amount,
            status = PaymentStatus.SUCCESS
        ))
    }

    fun markAsFailed(gatewayTransactionId: String?, reason: String? = null) {
        addTransaction(Transaction(
            gatewayTransactionId = gatewayTransactionId,
            amount = amount,
            status = PaymentStatus.FAILED
        ))
    }

    fun markAsRefunded(gatewayTransactionId: String) {
        addTransaction(Transaction(
            gatewayTransactionId = gatewayTransactionId,
            amount = amount,
            status = PaymentStatus.REFUNDED
        ))
    }
}
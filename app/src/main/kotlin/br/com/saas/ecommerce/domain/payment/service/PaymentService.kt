package br.com.saas.ecommerce.domain.payment.service

import br.com.saas.ecommerce.domain.payment.model.Payment
import br.com.saas.ecommerce.domain.payment.model.PaymentGateway
import br.com.saas.ecommerce.domain.payment.repository.PaymentRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun initiatePayment(orderId: UUID, tenantId: String, userId: UUID, amount: BigDecimal, gateway: PaymentGateway): Payment {
        val payment = Payment(
            tenantId = tenantId,
            orderId = orderId,
            userId = userId,
            gateway = gateway,
            amount = amount
        )
        return paymentRepository.save(payment)
    }

    fun confirmPayment(paymentId: UUID, tenantId: String, gatewayTransactionId: String): Payment {
        val payment = getPayment(paymentId, tenantId)
        payment.markAsSuccess(gatewayTransactionId)
        return paymentRepository.save(payment)
    }

    fun failPayment(paymentId: UUID, tenantId: String, gatewayTransactionId: String?, reason: String?): Payment {
        val payment = getPayment(paymentId, tenantId)
        payment.markAsFailed(gatewayTransactionId, reason)
        return paymentRepository.save(payment)
    }

    fun refundPayment(paymentId: UUID, tenantId: String, gatewayTransactionId: String): Payment {
        val payment = getPayment(paymentId, tenantId)
        payment.markAsRefunded(gatewayTransactionId)
        return paymentRepository.save(payment)
    }

    fun getPayment(paymentId: UUID, tenantId: String): Payment {
        return paymentRepository.findByIdAndTenant(paymentId, tenantId)
            ?: throw IllegalArgumentException("Pagamento não encontrado")
    }

    fun getPaymentByOrder(orderId: UUID, tenantId: String): Payment? {
        return paymentRepository.findByOrderIdAndTenant(orderId, tenantId)
    }
}
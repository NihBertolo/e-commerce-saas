package br.com.saas.ecommerce.domain.payment.provider

interface PaymentProvider {
    fun processPayment(request: PaymentRequest, config: TenantPaymentConfig): PaymentResponse
    fun refund(request: RefundRequest, config: TenantPaymentConfig): PaymentResponse
    fun supports(method: PaymentMethod): Boolean
}
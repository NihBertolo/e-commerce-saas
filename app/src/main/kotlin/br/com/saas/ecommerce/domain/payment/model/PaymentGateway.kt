package br.com.saas.ecommerce.domain.payment.model

enum class PaymentGateway {
    STRIPE,
    PAYPAL,
    MERCADO_PAGO,
    PIX
}
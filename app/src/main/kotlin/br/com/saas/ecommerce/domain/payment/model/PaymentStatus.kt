package br.com.saas.ecommerce.domain.payment.model

enum class PaymentStatus {
    PENDING,       // Aguardando confirmação
    PROCESSING,    // Gateway está processando
    SUCCESS,       // Pago com sucesso
    FAILED,        // Falhou (cartão recusado, Pix expirado, etc)
    REFUNDED,      // Estornado
    CANCELED       // Cancelado manualmente
}
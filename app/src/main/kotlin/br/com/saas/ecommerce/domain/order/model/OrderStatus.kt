package br.com.saas.ecommerce.domain.order.model

enum class OrderStatus {
    CREATED,       // Pedido criado
    CONFIRMED,     // Pagamento confirmado
    PROCESSING,    // Separando itens
    SHIPPED,       // Enviado
    DELIVERED,     // Entregue ao cliente
    CANCELED       // Cancelado
}
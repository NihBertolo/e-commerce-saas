package br.com.saas.ecommerce.domain.user.model

enum class UserRole {
    STORE_OWNER,   // dono do tenant
    STORE_ADMIN,   // gerencia catálogo/pedidos/usuários
    STORE_STAFF,   // atendimento/fulfillment
    ANALYST        // leitura/relatórios
}

enum class Permission {
    MANAGE_CATALOG, MANAGE_ORDERS, MANAGE_USERS, VIEW_REPORTS, MANAGE_PRICING
}
package br.com.saas.ecommerce.tenant

data class TenantPaymentConfig(
    val tenantId: String,
    val provider: String, // stripe, pagarme, mercadopago
    val apiKey: String,
    val merchantAccountId: String? = null, // usado em gateways como Stripe Connect
    val webhookSecret: String? = null
)
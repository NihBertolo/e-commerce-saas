package br.com.saas.ecommerce.tenant

interface TenantAwareEntity {
    val tenantId: String
}
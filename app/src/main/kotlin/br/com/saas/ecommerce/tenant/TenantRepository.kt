package br.com.saas.ecommerce.tenant

import java.util.*

interface TenantRepository {
    fun save(tenant: Tenant): Tenant
    fun findById(id: UUID): Tenant?
    fun findBySlug(slug: String): Tenant?
}

package br.com.saas.ecommerce.domain.catalog.model

import br.com.saas.ecommerce.tenant.TenantAwareEntity
import java.util.*

data class Category(
    val id: UUID = UUID.randomUUID(),
    override val tenantId: String,
    val name: String,
    val description: String? = null
) : TenantAwareEntity
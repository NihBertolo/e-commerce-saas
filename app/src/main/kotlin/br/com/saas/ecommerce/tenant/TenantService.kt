package br.com.saas.ecommerce.tenant

import org.springframework.stereotype.Service
import java.util.*

@Service
class TenantService(private val tenantRepo: TenantRepository) {

    fun createTenant(slug: String, name: String): Tenant {
        require(slug.matches(Regex("^[a-z0-9-]{3,50}$"))) { "Slug inválido" }
        require(tenantRepo.findBySlug(slug) == null) { "Slug já utilizado" }
        return tenantRepo.save(Tenant(slug = slug, name = name))
    }

    fun getBySlug(slug: String): Tenant =
        tenantRepo.findBySlug(slug) ?: throw IllegalArgumentException("Tenant não encontrado")

    fun getById(id: UUID): Tenant =
        tenantRepo.findById(id) ?: throw IllegalArgumentException("Tenant não encontrado")
}
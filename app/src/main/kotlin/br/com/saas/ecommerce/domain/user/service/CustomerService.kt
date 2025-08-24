package br.com.saas.ecommerce.domain.user.service

import br.com.saas.ecommerce.domain.user.model.Customer
import br.com.saas.ecommerce.domain.user.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(private val repo: CustomerRepository) {

    fun register(tenantId: String, email: String, name: String): Customer {
        require(repo.findByEmailAndTenant(email, tenantId) == null) { "Email já cadastrado" }
        return repo.save(Customer(internalTenantId = tenantId, email = email.lowercase(), name = name))
    }

    fun updateProfile(tenantId: String, id: UUID, name: String, phone: String?): Customer {
        val current = repo.findByIdAndTenant(id, tenantId) ?: throw IllegalArgumentException("Cliente não encontrado")
        val updated = current.copy(name = name, phone = phone, updatedAt = java.time.Instant.now())
        return repo.save(updated)
    }

    fun findByEmail(tenantId: String, email: String) =
        repo.findByEmailAndTenant(email.lowercase(), tenantId)

    fun search(tenantId: String, q: String, limit: Int = 50) =
        repo.searchByName(tenantId, q, limit)
}
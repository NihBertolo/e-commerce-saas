package br.com.saas.ecommerce.domain.user.repository

import br.com.saas.ecommerce.domain.user.model.Customer
import java.util.*

interface CustomerRepository {
    fun save(customer: Customer): Customer
    fun findByIdAndTenant(id: UUID, tenantId: String): Customer?
    fun findByEmailAndTenant(email: String, tenantId: String): Customer?
    fun searchByName(tenantId: String, q: String, limit: Int = 50): List<Customer>
}
package br.com.saas.ecommerce.domain.user.repository

import br.com.saas.ecommerce.domain.user.model.UserAccount
import java.util.*

interface UserAccountRepository {
    fun save(user: UserAccount): UserAccount
    fun findByIdAndTenant(id: UUID, tenantId: String): UserAccount?
    fun findByEmailAndTenant(email: String, tenantId: String): UserAccount?
    fun listByTenant(tenantId: String): List<UserAccount>
    fun disable(userId: UUID, tenantId: String)
}
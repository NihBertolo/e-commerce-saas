package br.com.saas.ecommerce.domain.user.model

import br.com.saas.ecommerce.tenant.TenantAwareEntity
import java.time.Instant
import java.util.*

data class UserAccount(
    val id: UUID = UUID.randomUUID(),
    override val tenantId: String,                 // chave do tenant (slug ou UUID string)
    val email: String,                    // único por tenant
    var name: String,
    var roles: MutableSet<UserRole> = mutableSetOf(UserRole.STORE_ADMIN),
    val providers: MutableSet<String> = mutableSetOf(), // "google", "facebook", "linkedin", "password"
    var passwordHash: String? = null,     // opcional se usar só OAuth
    var emailVerified: Boolean = false,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now(),
    var disabled: Boolean = false
) : TenantAwareEntity {

    fun addRole(role: UserRole) { roles.add(role); updatedAt = Instant.now() }
    fun removeRole(role: UserRole) { roles.remove(role); updatedAt = Instant.now() }
}
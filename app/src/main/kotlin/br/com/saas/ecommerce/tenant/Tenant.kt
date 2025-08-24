package br.com.saas.ecommerce.tenant

import br.com.saas.ecommerce.domain.user.model.UserRole
import java.time.Instant
import java.util.*

data class Tenant(
    val id: UUID = UUID.randomUUID(),
    val slug: String,                 // ex: "minha-loja" (único global)
    val name: String,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now(),
    val settings: TenantSettings = TenantSettings()
)

data class TenantSettings(
    val currency: String = "BRL",
    val locale: String = "pt-BR",
    val allowGuestCheckout: Boolean = true,
    val requireEmailVerification: Boolean = true,
    val defaultUserRoles: Set<UserRole> = setOf(UserRole.STORE_ADMIN)
)
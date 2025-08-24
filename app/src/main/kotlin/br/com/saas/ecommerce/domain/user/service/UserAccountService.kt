package br.com.saas.ecommerce.domain.user.service

import br.com.saas.ecommerce.domain.user.model.UserAccount
import br.com.saas.ecommerce.domain.user.model.UserRole
import br.com.saas.ecommerce.domain.user.repository.UserAccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserAccountService(
    private val repo: UserAccountRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun inviteAdmin(tenantId: String, email: String, name: String, roles: Set<UserRole>): UserAccount {
        require(repo.findByEmailAndTenant(email, tenantId) == null) { "Email já existe no tenant" }
        val user = UserAccount(
            internalTenantId = tenantId,
            email = email.lowercase(),
            name = name,
            roles = roles.toMutableSet()
        )
        // Aqui você poderia disparar um e-mail de convite via n8n
        return repo.save(user)
    }

    fun registerPassword(tenantId: String, userId: UUID, rawPassword: String): UserAccount {
        val user = repo.findByIdAndTenant(userId, tenantId)
            ?: throw IllegalArgumentException("Usuário não encontrado")
        val updated = user.copy(passwordHash = passwordEncoder.encode(rawPassword))
        return repo.save(updated)
    }

    fun linkOAuthProvider(tenantId: String, userId: UUID, provider: String): UserAccount {
        val user = repo.findByIdAndTenant(userId, tenantId)
            ?: throw IllegalArgumentException("Usuário não encontrado")
        user.providers.add(provider)
        return repo.save(user)
    }

    fun disable(tenantId: String, userId: UUID) = repo.disable(userId, tenantId)

    fun getByEmail(tenantId: String, email: String) =
        repo.findByEmailAndTenant(email.lowercase(), tenantId)
}
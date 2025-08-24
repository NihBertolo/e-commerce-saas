package br.com.saas.ecommerce.common.model

object TenantContext {
    private val currentTenant = ThreadLocal<String>()

    fun setTenant(tenantId: String) {
        currentTenant.set(tenantId)
    }

    fun getTenant(): String {
        return currentTenant.get() ?: throw IllegalStateException("Tenant não definido no contexto")
    }

    fun clear() {
        currentTenant.remove()
    }
}
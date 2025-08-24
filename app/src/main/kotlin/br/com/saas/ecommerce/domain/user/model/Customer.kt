package br.com.saas.ecommerce.domain.user.model

import br.com.saas.ecommerce.tenant.TenantAwareEntity
import java.time.Instant
import java.util.*

data class Customer(
    val id: UUID = UUID.randomUUID(),
    override val tenantId: String,                // isolamento por loja
    val email: String,                   // único por tenant
    var name: String,
    var phone: String? = null,
    var defaultAddress: Address? = null,
    val addresses: MutableList<Address> = mutableListOf(),
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now(),
    var marketingOptIn: Boolean = false
) : TenantAwareEntity {

    fun addAddress(address: Address) { addresses.add(address); updatedAt = Instant.now() }
}

data class Address(
    val street: String,
    val number: String?,
    val complement: String?,
    val district: String?,
    val city: String,
    val state: String,
    val country: String = "BR",
    val postalCode: String
)
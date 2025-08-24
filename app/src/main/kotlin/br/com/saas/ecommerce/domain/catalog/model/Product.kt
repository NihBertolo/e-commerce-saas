package br.com.saas.ecommerce.domain.catalog.model

import br.com.saas.ecommerce.tenant.TenantAwareEntity
import java.time.Instant
import java.util.*

data class Product(
    val id: UUID = UUID.randomUUID(),
    override val tenantId: String,
    val sku: String, // código único por tenant
    var name: String,
    var description: String,
    var price: Double,
    var stockQuantity: Int,
    var category: Category?,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now()
) : TenantAwareEntity {

    fun decreaseStock(quantity: Int) {
        require(quantity > 0) { "Quantidade deve ser positiva" }
        if (stockQuantity < quantity) {
            throw IllegalStateException("Estoque insuficiente para o produto $sku")
        }
        stockQuantity -= quantity
        updatedAt = Instant.now()
    }

    fun increaseStock(quantity: Int) {
        require(quantity > 0) { "Quantidade deve ser positiva" }
        stockQuantity += quantity
        updatedAt = Instant.now()
    }

    fun changePrice(newPrice: Double) {
        require(newPrice >= 0) { "Preço não pode ser negativo" }
        price = newPrice
        updatedAt = Instant.now()
    }
}
package br.com.saas.ecommerce.domain.catalog.repository

import br.com.saas.ecommerce.domain.catalog.model.Product
import java.util.*

interface ProductRepository {
    fun save(product: Product): Product
    fun findByIdAndTenant(id: UUID, tenantId: String): Product?
    fun findBySkuAndTenant(sku: String, tenantId: String): Product?
    fun findAllByTenant(tenantId: String): List<Product>
    fun delete(product: Product)
}
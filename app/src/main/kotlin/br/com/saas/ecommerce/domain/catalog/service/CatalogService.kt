package br.com.saas.ecommerce.domain.catalog.service

import br.com.saas.ecommerce.domain.catalog.model.Product
import br.com.saas.ecommerce.domain.catalog.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CatalogService(private val productRepository: ProductRepository) {

    fun createProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun updateProduct(product: Product): Product {
        val existing = productRepository.findByIdAndTenant(product.id, product.tenantId)
            ?: throw IllegalArgumentException("Produto não encontrado")
        return productRepository.save(product.copy(
            createdAt = existing.createdAt,
            updatedAt = java.time.Instant.now()
        ))
    }

    fun getProductById(productId: UUID, tenantId: String): Product {
        return productRepository.findByIdAndTenant(productId, tenantId)
            ?: throw IllegalArgumentException("Produto não encontrado")
    }

    fun getAllProducts(tenantId: String): List<Product> {
        return productRepository.findAllByTenant(tenantId)
    }

    fun deleteProduct(productId: UUID, tenantId: String) {
        val product = productRepository.findByIdAndTenant(productId, tenantId)
            ?: throw IllegalArgumentException("Produto não encontrado")
        productRepository.delete(product)
    }
}
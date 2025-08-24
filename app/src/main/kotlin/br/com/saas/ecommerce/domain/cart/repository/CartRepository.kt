package br.com.saas.ecommerce.domain.cart.repository

import br.com.saas.ecommerce.domain.cart.model.Cart
import java.util.*

interface CartRepository {
    fun save(cart: Cart): Cart
    fun findByIdAndTenant(id: UUID, tenantId: String): Cart?
    fun findByUserAndTenant(userId: String, tenantId: String): Cart?
    fun delete(cart: Cart)
}
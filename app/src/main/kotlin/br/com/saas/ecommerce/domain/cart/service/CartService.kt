package br.com.saas.ecommerce.domain.cart.service

import br.com.saas.ecommerce.domain.cart.model.Cart
import br.com.saas.ecommerce.domain.cart.model.CartItem
import br.com.saas.ecommerce.domain.cart.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(private val cartRepository: CartRepository) {

    fun getOrCreateCart(userId: String, tenantId: String): Cart {
        return cartRepository.findByUserAndTenant(userId, tenantId)
            ?: Cart(tenantId = tenantId, userId = userId).also { cartRepository.save(it) }
    }

    fun addItem(userId: String, tenantId: String, item: CartItem): Cart {
        val cart = getOrCreateCart(userId, tenantId)
        cart.addItem(item)
        return cartRepository.save(cart)
    }

    fun removeItem(userId: String, tenantId: String, productId: String): Cart {
        val cart = getOrCreateCart(userId, tenantId)
        cart.removeItem(productId)
        return cartRepository.save(cart)
    }

    fun clearCart(userId: String, tenantId: String): Cart {
        val cart = getOrCreateCart(userId, tenantId)
        cart.clear()
        return cartRepository.save(cart)
    }

    fun getCart(userId: String, tenantId: String): Cart =
        getOrCreateCart(userId, tenantId)
}
package br.com.saas.ecommerce.domain.cart.model

import java.math.BigDecimal

data class CartItem(
    val productId: String,
    val name: String,
    val price: BigDecimal,
    var quantity: Int
)
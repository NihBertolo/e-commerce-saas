package br.com.saas.ecommerce.security

object SecurityConstants {
    const val JWT_SECRET = "YOUR_SECRET_KEY" // carregar de env
    const val JWT_EXPIRATION_MS = 86400000L // 1 dia
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    const val ROLE_USER = "USER"
    const val ROLE_ADMIN = "ADMIN"
}
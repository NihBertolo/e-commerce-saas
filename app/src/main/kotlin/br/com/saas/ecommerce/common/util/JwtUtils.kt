package br.com.saas.ecommerce.common.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts

object JwtUtils {

    fun extractClaims(token: String, secret: String): Claims {
        return Jwts.parser()
            .setSigningKey(secret.toByteArray())
            .parseClaimsJws(token)
            .body
    }

    fun getUserId(token: String, secret: String): String? {
        return extractClaims(token, secret).subject
    }

    fun getTenantId(token: String, secret: String): String? {
        return extractClaims(token, secret)["tenant"] as? String
    }
}
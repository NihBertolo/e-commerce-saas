package br.com.saas.ecommerce.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {

    fun generateToken(username: String, roles: List<String>): String {
        val claims = Jwts.claims().setSubject(username)
        (claims as MutableMap<String, Any>)["roles"] = roles
        val now = Date()
        val expiryDate = Date(now.time + SecurityConstants.JWT_EXPIRATION_MS)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
            .compact()
    }

    fun getUsernameFromToken(token: String): String {
        return Jwts.parser()
            .setSigningKey(SecurityConstants.JWT_SECRET)
            .parseClaimsJws(token)
            .body.subject
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (ex: Exception) {
            return false
        }
    }
}
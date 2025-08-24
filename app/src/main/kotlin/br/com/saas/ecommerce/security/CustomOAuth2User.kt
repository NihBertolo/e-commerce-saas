package br.com.saas.ecommerce.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2User(private val oauth2User: OAuth2User) : OAuth2User {

    override fun getAttributes(): Map<String, Any> = oauth2User.attributes
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getName(): String = oauth2User.name
    val email: String get() = oauth2User.attributes["email"] as String
}
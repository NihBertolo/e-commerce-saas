package br.com.saas.ecommerce.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class OAuth2LoginSuccessHandler(private val jwtUtils: JwtUtils) : ServerAuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        webFilterExchange: WebFilterExchange,
        authentication: Authentication
    ): Mono<Void> {
        val token = jwtUtils.generateToken(authentication.name, listOf(SecurityConstants.ROLE_USER))
        webFilterExchange.exchange.response.headers.add("Authorization", SecurityConstants.TOKEN_PREFIX + token)
        return Mono.empty()
    }
}
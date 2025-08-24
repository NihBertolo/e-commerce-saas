package br.com.saas.ecommerce.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationFilter(private val jwtUtils: JwtUtils) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val header = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        if (header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            val token = header.replace(SecurityConstants.TOKEN_PREFIX, "")
            if (jwtUtils.validateToken(token)) {
                val username = jwtUtils.getUsernameFromToken(token)
                val auth = UsernamePasswordAuthenticationToken(username, null, emptyList())
                val context = SecurityContextImpl(auth)
                return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)))
            }
        }
        return chain.filter(exchange)
    }
}
package br.com.saas.ecommerce.security

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class OAuth2LoginFailureHandler : ServerAuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        webFilterExchange: WebFilterExchange?,
        exception: AuthenticationException?
    ): Mono<Void> {
        val response = webFilterExchange?.exchange?.response
        response?.statusCode = HttpStatus.UNAUTHORIZED
        // Opcional: adicionar mensagem ao corpo da resposta
        // val buffer = response.bufferFactory().wrap("Falha na autenticação".toByteArray())
        // return response.writeWith(Mono.just(buffer))
        return response!!.setComplete()
    }

}
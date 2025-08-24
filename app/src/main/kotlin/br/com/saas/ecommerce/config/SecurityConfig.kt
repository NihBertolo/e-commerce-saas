package br.com.saas.ecommerce.config

import br.com.saas.ecommerce.security.OAuth2LoginSuccessHandler
import br.com.saas.ecommerce.security.OAuth2UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class SecurityConfig(
    private val oauth2UserService: OAuth2UserServiceImpl,
    private val successHandler: OAuth2LoginSuccessHandler
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.csrf().disable()
            .authorizeExchange()
            .pathMatchers("/api/admin/**").hasRole("ADMIN")
            .pathMatchers("/api/**").authenticated()
            .anyExchange().permitAll()
            .and()
            .oauth2Login()
            .authenticationSuccessHandler(successHandler)
            .userInfoEndpoint()
            .userService(oauth2UserService)
        return http.build()
    }
}
package br.com.saas.ecommerce.security

import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OAuth2UserServiceImpl : ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): Mono<OAuth2User> {
        val provider = userRequest.clientRegistration.registrationId
        val oAuth2User = Mono.just(userRequest.accessToken) // usar WebClient se quiser pegar info extra
        return oAuth2User.map { CustomOAuth2User(it as OAuth2User) }
    }
}

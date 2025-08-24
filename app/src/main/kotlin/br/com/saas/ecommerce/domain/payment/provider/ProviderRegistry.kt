package br.com.saas.ecommerce.domain.payment.provider

import org.springframework.stereotype.Component

@Component
class ProviderRegistry(
    private val providers: List<PaymentProvider>
) {
    fun getProvider(providerName: String): PaymentProvider {
        return providers.firstOrNull { it.javaClass.simpleName.contains(providerName, ignoreCase = true) }
            ?: throw IllegalArgumentException("No provider registered for $providerName")
    }
}
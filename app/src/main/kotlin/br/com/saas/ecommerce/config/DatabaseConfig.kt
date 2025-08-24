package br.com.saas.ecommerce.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.r2dbc.core.DatabaseClient

@Configuration
class DatabaseConfig(private val connectionFactory: ConnectionFactory) {

    @Bean
    fun r2dbcEntityTemplate(): R2dbcEntityTemplate {
        return R2dbcEntityTemplate(DatabaseClient.create(connectionFactory))
    }
}
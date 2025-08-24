package br.com.saas.ecommerce.common.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtils {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("UTC"))

    fun now(): Instant = Instant.now()

    fun format(instant: Instant): String = formatter.format(instant)
}
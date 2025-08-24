package br.com.saas.ecommerce.common.exception

import br.com.saas.ecommerce.common.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("NOT_FOUND", ex.message ?: ""), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorized(ex: UnauthorizedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("UNAUTHORIZED", ex.message ?: ""), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusiness(ex: BusinessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("BUSINESS_ERROR", ex.message ?: ""), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneral(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("INTERNAL_ERROR", ex.message ?: "Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
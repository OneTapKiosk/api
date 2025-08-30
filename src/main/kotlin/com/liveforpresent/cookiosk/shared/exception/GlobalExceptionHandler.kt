package com.liveforpresent.cookiosk.shared.exception

import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import jakarta.persistence.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<BaseApiResponse<Any>> {
        val customExceptionCode = ex.code
        log.error("CustomException: {} - {}", customExceptionCode.code, customExceptionCode.message)
        val apiResponse = BaseApiResponse<Any>(
            success = false,
            message = customExceptionCode.message,
            data = ex.data,
            errorCode = customExceptionCode.code
        )
        return ResponseEntity.badRequest().body(apiResponse)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<BaseApiResponse<Unit>> {
        val apiResponse = BaseApiResponse<Unit>(
            success = false,
            message = "${ex.message}",
            errorCode = HttpStatus.INTERNAL_SERVER_ERROR
        )
        return ResponseEntity(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<BaseApiResponse<Unit>> {
        val apiResponse = BaseApiResponse<Unit>(
            success = false,
            message = "${ex.message}",
            errorCode = HttpStatus.INTERNAL_SERVER_ERROR
        )
        return ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(ex: EntityNotFoundException): ResponseEntity<BaseApiResponse<Unit>> {
        val apiResponse = BaseApiResponse<Unit>(
            success = false,
            message = "${ex.message}",
            errorCode = HttpStatus.NOT_FOUND
        )
        return ResponseEntity(apiResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(ex: AccessDeniedException): ResponseEntity<BaseApiResponse<Unit>> {
        val apiResponse = BaseApiResponse<Unit>(
            success = false,
            message = "${ex.message}",
            errorCode = HttpStatus.UNAUTHORIZED
        )
        return ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED)
    }
}

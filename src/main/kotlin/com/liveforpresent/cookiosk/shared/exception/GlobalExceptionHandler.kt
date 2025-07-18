package com.liveforpresent.cookiosk.shared.exception

import com.liveforpresent.cookiosk.shared.core.presentation.BaseApiResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ControllerAdvice
    class GlobalExceptionHandler {

        @ExceptionHandler(RuntimeException::class)
        fun handleRuntimeException(ex: RuntimeException): ResponseEntity<BaseApiResponse<Unit>> {
            val apiResponse = BaseApiResponse<Unit>(
                success = false,
                message = "${ex.message}",
                errorCode = "INTERNAL_SERVER_ERROR"
            )
            return ResponseEntity(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR)
        }

        @ExceptionHandler(IllegalArgumentException::class)
        fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<BaseApiResponse<Unit>> {
            val apiResponse = BaseApiResponse<Unit>(
                success = false,
                message = "${ex.message}",
                errorCode = "INVALID_ARGUMENT"
            )
            return ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST)
        }

        @ExceptionHandler(EntityNotFoundException::class)
        fun handleEntityNotFound(ex: EntityNotFoundException): ResponseEntity<BaseApiResponse<Unit>> {
            val apiResponse = BaseApiResponse<Unit>(
                success = false,
                message = "${ex.message}",
                errorCode = "NOT_FOUND"
            )
            return ResponseEntity(apiResponse, HttpStatus.NOT_FOUND)
        }

        @ExceptionHandler(AccessDeniedException::class)
        fun handleAccessDenied(ex: AccessDeniedException): ResponseEntity<BaseApiResponse<Unit>> {
            val apiResponse = BaseApiResponse<Unit>(
                success = false,
                message = "${ex.message}",
                errorCode = "PERMISSION_DENIED"
            )
            return ResponseEntity(apiResponse, HttpStatus.UNAUTHORIZED)
        }
    }
}

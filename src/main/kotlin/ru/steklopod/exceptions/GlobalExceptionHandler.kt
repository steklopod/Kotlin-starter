package ru.steklopod.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException) =
        ResponseEntity(
            ErrorDto(
                ErrorCodeDto.NOT_FOUND,
                "Resource not found"
            ), HttpStatus.NOT_FOUND)

    @ExceptionHandler(ValidationException::class)
    fun alreadyExists(ex: ValidationException) =
        ResponseEntity(
            ErrorDto(
                ErrorCodeDto.VALIDATION_ERROR,
                ex.message
            ), HttpStatus.BAD_REQUEST)
}



class ValidationException(message: String) : RuntimeException(message)
class NotFoundException(message: String) : RuntimeException(message)

data class ErrorDto(
    val errorCode: ErrorCodeDto?,
    val message: String?
)

enum class ErrorCodeDto {
    NOT_FOUND,
    VALIDATION_ERROR
}

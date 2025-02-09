package ru.sinitsynme.furnituretasktracker.exception

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommonExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound(ex: EntityNotFoundException) =
        ResponseEntity
            .status(NOT_FOUND)
            .body(RestExceptionResponse(ex.message));

    @ExceptionHandler(IllegalActionException::class)
    fun handleNotFound(ex: IllegalActionException) =
        ResponseEntity
            .status(BAD_REQUEST)
            .body(RestExceptionResponse(ex.message));
}

data class RestExceptionResponse(val message: String?)
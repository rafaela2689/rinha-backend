package com.rafaela.rinhabackend.controller

import com.rafaela.rinhabackend.exception.PessoaApelidoAlreadyExistsException
import com.rafaela.rinhabackend.exception.InvalidSyntaxException
import com.rafaela.rinhabackend.exception.PessoaNotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<*> {
        logger.error(exception) { "Unexpected error 500: ${exception.message}" }
        return ResponseEntity(exception.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleMissingParameterException(exception: HttpMessageNotReadableException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(PessoaApelidoAlreadyExistsException::class)
    fun handleApelidoAlreadyExists(exception: PessoaApelidoAlreadyExistsException): ResponseEntity<*> =
        ResponseEntity(exception.message, HttpStatus.UNPROCESSABLE_ENTITY)

    @ExceptionHandler(InvalidSyntaxException::class)
    fun handleInvalidSyntaxException(exception: InvalidSyntaxException): ResponseEntity<*> =
        ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(PessoaNotFoundException::class)
    fun handlePessoaNotFound(exception: PessoaNotFoundException): ResponseEntity<*> =
        ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
}
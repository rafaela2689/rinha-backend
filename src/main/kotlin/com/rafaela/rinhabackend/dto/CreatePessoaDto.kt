package com.rafaela.rinhabackend.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate
import org.hibernate.validator.constraints.Length
import org.springframework.format.annotation.DateTimeFormat

class CreatePessoaDto(
    @field:Length(max = 32)
    @field:NotBlank
    val apelido: String,
    @field:Length(max = 100)
    @field:NotBlank
    val nome: String,
    @field:DateTimeFormat(pattern = "yyyy-MM-dd")
    val nascimento: LocalDate,
    @field:NotEmpty
    val stack: List<String>?
) {
    fun validate(): ErrorType? {
        val pattern = "\\d+"
        if (stack != null && stack.any { it.isEmpty() || (it.isNotEmpty() && it.length > 32) }) {
            return ErrorType.INVALID_REQUEST
        }
        if (Regex(pattern).matches(nome) || Regex(pattern).matches(apelido)) {
            return ErrorType.INVALID_SYNTAX
        }

        if (stack?.any { Regex(pattern).matches(it) } == true) {
            return ErrorType.INVALID_SYNTAX
        }
        return null
    }
}

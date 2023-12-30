package com.rafaela.rinhabackend

import com.rafaela.rinhabackend.dto.CreatePessoaDto
import com.rafaela.rinhabackend.dto.ErrorType
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreatePessoaDtoTest {

    @Test
    fun validateNomeNotDigit() {
        val pessoa = CreatePessoaDto(
            apelido = "test",
            nome = "1111",
            nascimento = LocalDate.now(),
            stack = listOf("kotlin")
        )

        val result = pessoa.validate()

        assertThat(result).isEqualTo(ErrorType.INVALID_SYNTAX)
    }
}
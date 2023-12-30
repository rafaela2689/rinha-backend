package com.rafaela.rinhabackend.dto

import com.rafaela.rinhabackend.model.Pessoa
import java.time.LocalDate

class PessoaDto(
    val id: String,
    val apelido: String,
    val nome: String,
    val nascimento: LocalDate,
    val stack: List<String>?
) {
    companion object {
        fun of(pessoa: Pessoa) =
            PessoaDto(
                id = pessoa.id.toString(),
                apelido = pessoa.apelido,
                nome = pessoa.nome,
                nascimento = pessoa.nascimento,
                stack = pessoa.stack
            )
    }
}

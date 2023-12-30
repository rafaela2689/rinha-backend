package com.rafaela.rinhabackend.service

import com.rafaela.rinhabackend.dto.CreatePessoaDto
import com.rafaela.rinhabackend.dto.ErrorType
import com.rafaela.rinhabackend.model.Pessoa
import com.rafaela.rinhabackend.dto.PessoaDto
import com.rafaela.rinhabackend.exception.InvalidSyntaxException
import com.rafaela.rinhabackend.exception.PessoaApelidoAlreadyExistsException
import com.rafaela.rinhabackend.exception.PessoaNotFoundException
import com.rafaela.rinhabackend.repository.PessoaRepository
import java.util.UUID
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class PessoaService(
    val pessoaRepository: PessoaRepository
) {
    fun salvar(pessoaDto: CreatePessoaDto): PessoaDto {
        val validateResult = pessoaDto.validate()
        when (validateResult) {
            ErrorType.INVALID_REQUEST -> throw IllegalArgumentException("Pessoa stack is invalid.")
            ErrorType.INVALID_SYNTAX -> throw InvalidSyntaxException()
            null -> logger.info { "No errors found." }
        }

        if (pessoaRepository.findByApelido(pessoaDto.apelido) != null) {
            throw PessoaApelidoAlreadyExistsException(pessoaDto.apelido)
        }

        val pessoa = Pessoa(
            id = UUID.randomUUID(),
            apelido = pessoaDto.apelido,
            nome = pessoaDto.nome,
            nascimento = pessoaDto.nascimento,
            stack = pessoaDto.stack
        )

        val pessoaCreated = pessoaRepository.save(pessoa)
        return PessoaDto(
            id = pessoaCreated.id.toString(),
            apelido = pessoaCreated.apelido,
            nome = pessoaCreated.nome,
            nascimento = pessoaCreated.nascimento,
            stack = pessoaCreated.stack
        )
    }

    fun getTotalPessoasCadastradas(): Long {
        return pessoaRepository.count()
    }

    fun getPessoaPorId(id: String): PessoaDto? {
        val pessoa = pessoaRepository.findById(UUID.fromString(id))
            .orElseThrow { PessoaNotFoundException("Pessoa not found!") }
        return PessoaDto.of(pessoa)
    }

    fun buscarPessoasPorTermo(t: String): List<PessoaDto> {
        val pessoas = pessoaRepository.searchByTerm(t)
        return pessoas.map { PessoaDto.of(it) }
    }

}
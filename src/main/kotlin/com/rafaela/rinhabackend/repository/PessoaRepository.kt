package com.rafaela.rinhabackend.repository

import com.rafaela.rinhabackend.model.Pessoa
import java.util.UUID
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PessoaRepository: CrudRepository<Pessoa, UUID> {

    @Query(value = "SELECT * from PESSOA p " +
            "WHERE p.search_term ILIKE %?1% " +
            "LIMIT 50",
        nativeQuery = true)
    fun searchByTerm(t: String): List<Pessoa>

    fun findByApelido(apelido: String): Pessoa?
}

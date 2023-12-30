package com.rafaela.rinhabackend.model

import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.UUID
import org.hibernate.annotations.Type

@Entity
class Pessoa(
    @Id
    val id: UUID?,
    @Column(
        length = 32,
        nullable = false
    )
    val apelido: String,
    @Column(
        length = 100,
        nullable = false
    )
    val nome: String,
    @Column(
        length = 100,
        nullable = false
    )
    val nascimento: LocalDate,
    @Type(JsonType::class)
    @Column(
        name = "stack",
        nullable = true,
        columnDefinition = "json"
    )
    val stack: List<String>?
)
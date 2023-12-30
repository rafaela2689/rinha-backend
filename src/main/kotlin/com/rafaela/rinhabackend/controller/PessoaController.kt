package com.rafaela.rinhabackend.controller

import com.rafaela.rinhabackend.dto.CreatePessoaDto
import com.rafaela.rinhabackend.dto.PessoaDto
import com.rafaela.rinhabackend.service.PessoaService
import jakarta.validation.Valid
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class PessoaController(
    val pessoaService: PessoaService,
    val assembler: PessoaModelAssembler
) {

    @PostMapping("/pessoas")
    fun criarPessoa(@RequestBody @Valid pessoaDto: CreatePessoaDto): ResponseEntity<EntityModel<PessoaDto>> {
        val entityModel: EntityModel<PessoaDto> = assembler.toModel(pessoaService.salvar(pessoaDto))
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel)
    }

    @GetMapping("/pessoas/{id}")
    fun getPessoaPorId(@PathVariable id: String): ResponseEntity<PessoaDto> {
        return ok(pessoaService.getPessoaPorId(id))
    }

    @GetMapping("/pessoas")
    fun buscarPessoasPorTermo(@RequestParam t: String?): ResponseEntity<List<PessoaDto>> {
        if (t == null || t.isEmpty()) {
            return badRequest().build()
        }
        return ok(pessoaService.buscarPessoasPorTermo(t))
    }

    @GetMapping("/contagem-pessoas")
    fun getTotalPessoasCadastradas(): ResponseEntity<Long> {
        return ok(pessoaService.getTotalPessoasCadastradas())
    }
}
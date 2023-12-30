package com.rafaela.rinhabackend.controller

import com.rafaela.rinhabackend.dto.PessoaDto
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component

@Component
class PessoaModelAssembler: RepresentationModelAssembler<PessoaDto, EntityModel<PessoaDto>> {
    override fun toModel(entity: PessoaDto): EntityModel<PessoaDto> {
        return EntityModel.of(entity,
            linkTo<PessoaController> {
                methodOn(PessoaController::class.java).getPessoaPorId(entity.id)
            }.withSelfRel()
        )
    }
}
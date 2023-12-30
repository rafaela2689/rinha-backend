package com.rafaela.rinhabackend.exception

class PessoaApelidoAlreadyExistsException(apelido: String) : RuntimeException("Pessoa with nickname $apelido already exists.")

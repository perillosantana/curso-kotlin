package br.com.perillo.teste.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class NewClientForm(
    @field:NotEmpty
    @field:NotNull
    val name: String,

    @field:NotEmpty
    @field:NotNull
    val email: String
)

package br.com.perillo.teste.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
data class AuthenticationForm (
        @field:NotEmpty
        @field:NotNull
        val login: String,

        @field:NotEmpty
        @field:NotNull
        var password: String
)

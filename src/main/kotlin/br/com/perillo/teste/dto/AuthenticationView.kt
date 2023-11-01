package br.com.perillo.teste.dto

import java.time.LocalDateTime

data class AuthenticationView (
        val id: Long?,
        val login: String,
        val password: String,
        val createIn: LocalDateTime,
        val lastInteractionIn: LocalDateTime
)
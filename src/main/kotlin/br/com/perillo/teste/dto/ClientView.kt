package br.com.perillo.teste.dto

import java.time.LocalDateTime

data class ClientView (
    val id: Long?,
    val name: String,
    val email: String,
    val createDate: LocalDateTime
)

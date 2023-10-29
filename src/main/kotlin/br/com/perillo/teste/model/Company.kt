package br.com.perillo.teste.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Company(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    val createDate: LocalDateTime = LocalDateTime.now(),
)

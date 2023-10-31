package br.com.perillo.teste.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    var email: String,
    val createdIn: LocalDateTime = LocalDateTime.now(),
    val lastInteractionIn: LocalDateTime = LocalDateTime.now()
)
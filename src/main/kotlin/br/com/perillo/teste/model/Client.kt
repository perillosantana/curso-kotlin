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
    @Column(name = "created_in")
    val createdIn: LocalDateTime = LocalDateTime.now(),
    @Column(name = "last_interaction_in")
    val lastInteractionIn: LocalDateTime = LocalDateTime.now()
)
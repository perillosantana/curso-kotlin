package br.com.perillo.teste.repository

import br.com.perillo.teste.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails

interface UserRepository: JpaRepository<User, Long> {
    fun findByLogin(login: String): UserDetails?
}
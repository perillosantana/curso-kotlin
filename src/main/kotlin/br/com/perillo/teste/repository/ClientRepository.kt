package br.com.perillo.teste.repository

import br.com.perillo.teste.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client, Long> {
}
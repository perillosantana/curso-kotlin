package br.com.perillo.teste.repository

import br.com.perillo.teste.model.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository: JpaRepository<Company, Long> {
}
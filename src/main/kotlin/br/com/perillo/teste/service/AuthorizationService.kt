package br.com.perillo.teste.service

import br.com.perillo.teste.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private var userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        return  userRepository.findByLogin(username);
    }
}
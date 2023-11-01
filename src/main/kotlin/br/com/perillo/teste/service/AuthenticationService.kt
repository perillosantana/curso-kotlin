package br.com.perillo.teste.service

import br.com.perillo.teste.dto.AuthenticationForm
import br.com.perillo.teste.dto.AuthenticationView
import br.com.perillo.teste.mapper.AuthenticationFormMapper
import br.com.perillo.teste.mapper.AuthenticationViewMapper
import br.com.perillo.teste.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthenticationService(
        private var userRepository: UserRepository,
        private  val authenticationFormMapper : AuthenticationFormMapper,
        private  val authenticationViewMapper : AuthenticationViewMapper,
) {
    fun register(form: AuthenticationForm): AuthenticationView {
        val encryptedPassword = BCryptPasswordEncoder().encode(form.password)
        form.password = encryptedPassword
        val client = authenticationFormMapper.map(form)
        userRepository.save(client)
        return authenticationViewMapper.map(client)
    }
}
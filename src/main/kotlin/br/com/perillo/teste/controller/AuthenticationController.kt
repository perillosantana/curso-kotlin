package br.com.perillo.teste.controller

import br.com.perillo.teste.dto.AuthenticationForm
import br.com.perillo.teste.dto.AuthenticationView
import br.com.perillo.teste.dto.LoginResponseView
import br.com.perillo.teste.repository.UserRepository
import br.com.perillo.teste.service.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("auth")
class AuthenticationController(
        val userRepository: UserRepository,
        val service: AuthenticationService,
) {
    @PostMapping("/login")
    fun login(
        @RequestBody @Valid form: AuthenticationForm,
    ): ResponseEntity<LoginResponseView> {
        val token = service.login(form)
        return ResponseEntity.ok(LoginResponseView(token))
    }

    @PostMapping("/register")
    fun register(
        @RequestBody @Valid form: AuthenticationForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AuthenticationView> {
        if(userRepository.findByLogin(form.login) != null) return ResponseEntity.badRequest().build()

        val userView = service.register(form)
        val uri = uriBuilder.path("/clients/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }
}
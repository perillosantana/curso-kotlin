package br.com.perillo.teste.controller


import br.com.perillo.teste.dto.AuthenticationForm
import br.com.perillo.teste.dto.AuthenticationView
import br.com.perillo.teste.dto.LoginResponseView
import br.com.perillo.teste.model.User
import br.com.perillo.teste.repository.UserRepository
import br.com.perillo.teste.service.AuthenticationService
import br.com.perillo.teste.service.TokenService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("auth")
class AuthenticationController(
        val authenticationManager: AuthenticationManager,
        val userRepository: UserRepository,
        val service: AuthenticationService,
) {
    @PostMapping("/login")
    fun login(
            @RequestBody @Valid form: AuthenticationForm,
            tokenService: TokenService,
    ): ResponseEntity<LoginResponseView> {
        val usernamePassword = UsernamePasswordAuthenticationToken(form.login, form.password)
        val auth = this.authenticationManager.authenticate(usernamePassword)
        val token: String = tokenService.generateToken(auth.principal as User)

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
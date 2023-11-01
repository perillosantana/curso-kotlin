package br.com.perillo.teste.infra.security

import br.com.perillo.teste.repository.UserRepository
import br.com.perillo.teste.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


@Component
class SecurityFilter : OncePerRequestFilter() {
    private lateinit var tokenService: TokenService
    private lateinit var userRepository: UserRepository

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = recoverToken(request)
        if (token != null) {
            val login = tokenService.validateToken(token)
            val user = userRepository.findByLogin(login)
            val authentication = UsernamePasswordAuthenticationToken(user, null, user?.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") ?: return null
        return authHeader.replace("Bearer ", "")
    }
}
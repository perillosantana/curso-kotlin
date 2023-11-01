package br.com.perillo.teste.infra.security


import br.com.perillo.teste.model.User
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


@Service
class TokenService {
    @Value(value = "\${api.security.token.secret}")
    private lateinit var secret: String

    fun generateToken(
            user: User,
    ): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.login)
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm)
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error while generating token", exception)
        }
    }

    fun validateToken(token: String?): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .subject
        } catch (exception: JWTVerificationException) {
            throw UsernameNotFoundException("Token JWT inválido ou expirado!")
        }
    }

    private fun genExpirationDate(): Instant {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }
}
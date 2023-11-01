package br.com.perillo.teste.mapper

import br.com.perillo.teste.dto.AuthenticationView
import br.com.perillo.teste.model.User
import org.springframework.stereotype.Component

@Component
class AuthenticationViewMapper: Mapper<User, AuthenticationView> {
    override fun map(t: User): AuthenticationView {
        return  AuthenticationView(
                id = t.id,
                login = t.login,
                password = t.password,
                createIn = t.createdIn,
                lastInteractionIn = t.lastInteractionIn
        )
    }
}
package br.com.perillo.teste.mapper

import br.com.perillo.teste.dto.AuthenticationForm
import br.com.perillo.teste.model.User
import org.springframework.stereotype.Component

@Component
class AuthenticationFormMapper: Mapper<AuthenticationForm, User> {
    override fun map(t: AuthenticationForm): User {
        return User(
                login = t.login,
                pass = t.password
        )
    }
}

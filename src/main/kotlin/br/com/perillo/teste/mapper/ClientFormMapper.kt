package br.com.perillo.teste.mapper

import br.com.perillo.teste.dto.NewClientForm
import br.com.perillo.teste.model.Client
import org.springframework.stereotype.Component

@Component
class ClientFormMapper: Mapper<NewClientForm, Client> {
    override fun map(t: NewClientForm): Client {
        return Client(
            name = t.name,
            email = t.email
        )
    }
}

package br.com.perillo.teste.mapper

import br.com.perillo.teste.dto.ClientView
import br.com.perillo.teste.model.Client
import org.springframework.stereotype.Component

@Component
class ClientViewMapper: Mapper<Client, ClientView> {
    override fun map(t: Client): ClientView {
        return  ClientView(
            id = t.id,
            name = t.name,
            email = t.email,
            createDate = t.createDate
        )
    }
}
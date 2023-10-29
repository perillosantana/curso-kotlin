package br.com.perillo.teste.service

import br.com.perillo.teste.dto.NewClientForm
import br.com.perillo.teste.dto.ClientView
import br.com.perillo.teste.dto.UpdateClientForm
import br.com.perillo.teste.exception.NotFoundException
import br.com.perillo.teste.mapper.ClientFormMapper
import br.com.perillo.teste.mapper.ClientViewMapper
import br.com.perillo.teste.model.Client
import br.com.perillo.teste.repository.ClientRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class ClientService(
    private var repository: ClientRepository,
    private  val clientViewMapper : ClientViewMapper,
    private  val clientFormMapper : ClientFormMapper,
    private  val notFoundMessage : String = "Cliente não encontrado"
) {
    fun list(): List<ClientView> {
        return repository.findAll().stream().map {t -> clientViewMapper.map(t)}.collect(Collectors.toList())
    }

    fun findClient(id: Long): ClientView {
        val client = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}

        return clientViewMapper.map(client)
    }

    fun register(form: NewClientForm): ClientView {
        val client = clientFormMapper.map(form)
        repository.save(client)
        return clientViewMapper.map(client)
    }

    fun update(form: UpdateClientForm): ClientView {
        val client = repository.findById(form.id).orElseThrow{NotFoundException(notFoundMessage)}
        client.name = form.name
        client.email= form.email
        return clientViewMapper.map(client)
    }

    fun remove(id: Long) {
        repository.deleteById(id)
    }
}
package br.com.perillo.teste.controller

import br.com.perillo.teste.dto.NewClientForm
import br.com.perillo.teste.dto.ClientView
import br.com.perillo.teste.dto.UpdateClientForm
import br.com.perillo.teste.service.ClientService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/clients")
class ClientController(private val service: ClientService) {
    @GetMapping
    fun listClient(): List<ClientView> {
        return service.list()
    }

    @GetMapping("/{id}")
    fun findClient(@PathVariable id: Long): ClientView {
        return service.findClient(id)
    }

    @PostMapping
    @Transactional
    fun register(
        @RequestBody @Valid form: NewClientForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ClientView> {
        val clientView = service.register(form)
        val uri = uriBuilder.path("/clients/${clientView.id}").build().toUri()
        return ResponseEntity.created(uri).body(clientView)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid form: UpdateClientForm): ResponseEntity<ClientView>  {
        val clientView = service.update(form)
        return ResponseEntity.ok(clientView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun remove(@PathVariable id: Long) {
        service.remove(id)
    }
}
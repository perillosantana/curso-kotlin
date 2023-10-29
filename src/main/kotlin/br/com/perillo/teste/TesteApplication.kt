package br.com.perillo.teste

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TesteApplication

fun main(args: Array<String>) {
	runApplication<TesteApplication>(*args)
}

package br.com.perillo.teste.exception

import java.lang.RuntimeException

class NotFoundException(message: String?): RuntimeException(message) {

}
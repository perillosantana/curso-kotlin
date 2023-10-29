package br.com.perillo.teste.mapper

interface Mapper<T, U> {
    fun map(t: T) : U
}

package dev.patrickdorn.realestatefinder.data.web.repository

interface ModelMapper<I, O> {
    fun map(input: I): O
}

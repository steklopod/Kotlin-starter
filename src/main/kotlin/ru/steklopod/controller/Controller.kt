package ru.steklopod.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import ru.steklopod.repositories.CustomerRepository

@RestController
class Controller(private val repository: CustomerRepository) {

    @GetMapping("/customers")
    fun findAll() = repository.findAll()

    @GetMapping("/customers/{lastName}")
    fun findByLastName(@PathVariable lastName: String) = repository.findByLastName(lastName)

    @RequestMapping("/raiseError")
    fun raiseError() {
        throw IllegalArgumentException("This shouldn't have happened")
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleError(e: IllegalArgumentException) = e.message
}
package ru

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import ru.steklopod.model.Customer
import ru.steklopod.repositories.CustomerRepository

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@SpringBootApplication
class Application {

    @Bean
    fun init(repository: CustomerRepository) = CommandLineRunner {
        repository.apply {
            save(Customer("Vladimir", "Putin"))
            save(Customer("Nikolay", "Eltsin"))
            save(Customer("Dima", "Koltovich"))
        }
    }

}

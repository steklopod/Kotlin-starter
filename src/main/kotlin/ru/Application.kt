package ru

import org.slf4j.LoggerFactory.getLogger
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
    private val log = getLogger(this::class.java)

    @Bean
    fun init(repository: CustomerRepository) = CommandLineRunner {

        repository.apply {
            save(Customer("Vladimir", "Putin"))
            save(Customer("Nikolay", "Eltsin"))
            save(Customer("Dima", "Koltovich"))

            log.info("Customers found with findAll(): \n-------------------------------")
            findAll()
                .forEach { log.info(it.toString()) }

            log.info("Customer found with findById(1L):\n--------------------------------")
            findById(1L)
                .ifPresent { log.info(it.toString()) }

            log.info("Customer found with findByLastName('Bauer'):\n--------------------------------------------")
            findByLastName("Putin")
                .forEach { log.info(it.toString()) }
        }
    }

}

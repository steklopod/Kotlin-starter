package ru

import org.slf4j.LoggerFactory
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

    private val log = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun init(repository: CustomerRepository) = CommandLineRunner {

        repository.apply {
            save(Customer("Jack", "Bauer"))
            save(Customer("Chloe", "O'Brian"))
            save(Customer("Kim", "Bauer"))
            save(Customer("David", "Palmer"))
            save(Customer("Michelle", "Dessler"))

            log.info("Customers found with findAll(): \n-------------------------------")
            findAll().forEach { log.info(it.toString()) }

            val customer = findById(1L)
            customer.ifPresent {
                log.info("Customer found with findById(1L):\n--------------------------------")
                log.info(it.toString())
            }

            log.info("Customer found with findByLastName('Bauer'):\n--------------------------------------------")
            findByLastName("Bauer").forEach { log.info(it.toString()) }
        }
    }

}

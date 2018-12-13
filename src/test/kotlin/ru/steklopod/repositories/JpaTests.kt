package ru.steklopod.repositories

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@TestInstance(PER_CLASS)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestEntityManager
internal class JpaTests(
    @Autowired private val repository: CustomerRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @BeforeAll
    fun setup() {
        // repository.apply {
        //            save(Customer("Vladimir", "Putin"))
        //            save(Customer("Nikolay", "Eltsin"))
        //            save(Customer("Dima", "Koltovich"))
        //                  }
    }

    @Test
    fun findAll() {
        log.info("Customers found with findAll(): \n-------------------------------")
        repository.findAll()
            .forEach { log.info(it.toString()) }
    }

    @Test
    fun findById() {
        log.info("Customer found with findById(1L):\n--------------------------------")
        repository.findById(1L)
            .ifPresent { log.info(it.toString()) }
    }

    @Test
    fun findByLastName() {
        log.info("Customer found with findByLastName('Bauer'):\n--------------------------------------------")
        repository.findByLastName("Putin")
            .forEach { log.info(it.toString()) }
    }

}


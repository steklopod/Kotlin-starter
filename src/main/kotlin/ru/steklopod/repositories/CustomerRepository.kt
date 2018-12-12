package ru.steklopod.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.steklopod.model.Customer

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
	fun findByLastName(lastName: String): Iterable<Customer>
}

package ru.steklopod.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Customer(

    val firstName: String,
    val lastName: String,
    @Id  @GeneratedValue
    var id: Long = -1
)

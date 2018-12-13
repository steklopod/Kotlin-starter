package ru.steklopod

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import ru.steklopod.model.Customer
import java.io.File
import java.nio.file.Files

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(PER_CLASS)
@AutoConfigureWebTestClient
internal class ApplicationTests(
    @Autowired private val restTemplate: TestRestTemplate,
    @Autowired private val webClient: WebTestClient,
    @Autowired private val mapper: ObjectMapper
) {
    lateinit var customer: Customer

    val URL = "/customers"
    val JSON_RESOURSCE_FOLDER = "json/files/"
    val content =
        """[{"firstName":"Jack","lastName":"Bauer","id":1},{"firstName":"Chloe","lastName":"O'Brian","id":2},{"firstName":"Kim","lastName":"Bauer","id":3},{"firstName":"David","lastName":"Palmer","id":4},{"firstName":"Michelle","lastName":"Dessler","id":5}]"""

    @BeforeAll
    fun setup() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val fileCustomerJson = ClassPathResource("${JSON_RESOURSCE_FOLDER}customer.json").file

        val reader = mapper.readerFor(Customer::class.java)
        val customereNode = mapper.readTree(toJsonString(fileCustomerJson)).get("data")

        customer = reader.readValue(customereNode)
        println("customer: $customer")
    }


    @Test
    fun findAll() {
        assertEquals(content, restTemplate.getForObject<String>(URL))
    }

    @Test
    fun webClient() {
        https@ //www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
        webClient.get().uri(URL)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Customer::class.java)
    }
}

internal fun toJsonString(fileResponse: File) = String(Files.readAllBytes(fileResponse.toPath()))

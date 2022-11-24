package no.exam.orderservice.integrationtest

import no.exam.orderservice.extensions.TestContainerExtension
import no.exam.orderservice.extensions.WireMockExtension
import org.hamcrest.Matchers
import org.json.JSONObject
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(TestContainerExtension::class, WireMockExtension::class)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class OrderServiceApplicationTests(@Autowired private val mockMvc: MockMvc) {

    val baseURL = "http://localhost:8080/api/order"


    @Test
    @Order(1)
    fun shouldPOSTorder() {
        val order = JSONObject()
            .put("orderOwner", "TEST POST")
            .put("orderAmount", 1000)

        mockMvc.post("$baseURL/newOrder") {
            contentType = MediaType.APPLICATION_JSON
            content = order
        }
            .andExpect { status { isOk() } }
            .andReturn()
    }

    @Test
    @Order(2)
    fun shouldGETorder() {
        mockMvc.get("$baseURL/1") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$.orderOwner", Matchers.containsString("TEST POST")) }
            .andExpect { jsonPath("$.orderAmount", Matchers.equalTo(1000)) }
            .andExpect { jsonPath("$.shippingReady", Matchers.equalTo(false)) }
    }

    @Test
    @Order(3)
    fun shouldUpdateOrder() {
            val updateOrder = JSONObject()
                .put("orderOwner", "TEST PUT")
                .put("orderAmount", 999)
                .put("shippingReady", true)

        mockMvc.put("$baseURL/1"){
            contentType = MediaType.APPLICATION_JSON
            content = updateOrder
        }
            .andExpect { status { isOk() } }
            .andExpect { jsonPath("$.orderOwner", Matchers.containsString("TEST PUT")) }
            .andExpect { jsonPath("$.orderAmount", Matchers.equalTo(999)) }
            .andExpect { jsonPath("$.shippingReady", Matchers.equalTo(true)) }
    }
}
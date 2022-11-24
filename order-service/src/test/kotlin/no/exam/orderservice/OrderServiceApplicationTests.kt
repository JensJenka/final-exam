package no.exam.orderservice

import no.exam.orderservice.extensions.TestContainerExtension
import no.exam.orderservice.extensions.WireMockExtension
import org.hamcrest.Matchers
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.`is`
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

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(TestContainerExtension::class, WireMockExtension::class)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class OrderServiceApplicationTests(@Autowired private val mockMvc: MockMvc) {

    @Test
    fun shouldGetOrderFromWireMock(){
        mockMvc
            .get("http://localhost:8080/api/order/1")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$.orderOwner", Matchers.`is`("TEST POST")) }
            .andExpect { jsonPath("$.orderAmount", Matchers.`is`(1000)) }
    }

    @Test
    fun shouldGetPaymentFromWireMock(){
        mockMvc
            .get("http://localhost:8080/api/payment/1")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { jsonPath("$.orderId", Matchers.`is`(1)) }
            .andExpect { jsonPath("$.payed", Matchers.`is`(false)) }
    }

}

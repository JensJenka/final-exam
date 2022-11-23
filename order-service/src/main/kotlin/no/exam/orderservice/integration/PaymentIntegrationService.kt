package no.exam.orderservice.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class PaymentIntegrationService(@Autowired private val restTemplate: RestTemplate) {

    val paymentURL = "http://payment-service/api/payment/http"

    fun sendHttpCallToPaymentService(message: String) {
    val response: ResponseEntity<String> = restTemplate.getForEntity("$paymentURL/$message", ResponseEntity::class)
    println("Response from PaymentIntegration, sendHttpCallToPayment: ${response.body}")
    }

    fun sendHttpGETPayment(id: String ) {
    val response: ResponseEntity<String> = restTemplate.getForEntity("$paymentURL/http/$id", ResponseEntity::class)
    println("WHAT IS THIS: ${response.body}")
    }

    fun createPaymentHTTPrequest(orderId: String) {
        val response: ResponseEntity<String> = restTemplate.getForEntity("$paymentURL/$orderId", ResponseEntity::class)
        println("ID successfully transferred to Payment-Service: ${response.body}")
    }

}
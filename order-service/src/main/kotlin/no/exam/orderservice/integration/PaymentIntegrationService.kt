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
    val response: ResponseEntity<String> = restTemplate.getForEntity("$paymentURL/message/$message", ResponseEntity::class)
    println("Response from PaymentIntegration, Call from Order->Payment: ${response.body}")
    }

    fun createPaymentHTTPrequest(orderId: String) {
        val response: ResponseEntity<String> = restTemplate.getForEntity("$paymentURL/$orderId", ResponseEntity::class)
        println("ID successfully transferred to Payment-Service: ${response.body}")
    }

    fun deletePaymentHTTP(orderId: String) {
        println("IS IT HERE " + orderId)
        val url = "$paymentURL/delete"
        println("IS IT HERE2")

        restTemplate.delete(url, orderId)
        println("IS IT HERE3")
        println("Is deleted")
        val response: ResponseEntity<String> = restTemplate.getForEntity("$paymentURL/{$orderId}/delete", ResponseEntity::class)
        println("DELETE ID successfully transferred to Payment-Service: ${response.body + response.statusCode}")
    }

}
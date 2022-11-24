package no.exam.paymentservice.integration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PaymentSender(@Autowired private val rabbitTemplate: RabbitTemplate) {

    fun sendMessage(message: String) {
        rabbitTemplate.convertAndSend("order_queue", message)
    }

    fun sendPaymentUpdateToShippingService(paymentId: String) {
        rabbitTemplate.convertAndSend("shipping_update_queue", paymentId)
    }
}
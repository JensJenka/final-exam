package no.exam.orderservice.integration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderSender(@Autowired private val rabbitTemplate: RabbitTemplate) {

    fun sendMessage(message: String) {
        rabbitTemplate.convertAndSend("payment_queue", message)
    }

    fun sendOrdIdToPayment(ordreId: String) {
        rabbitTemplate.convertAndSend("payment_queue", ordreId)
    }

    fun sendOrder(id:Long){
        rabbitTemplate.convertAndSend("order_queue", id)
    }
}
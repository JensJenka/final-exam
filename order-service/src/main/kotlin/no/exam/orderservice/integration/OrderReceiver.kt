package no.exam.orderservice.integration

import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["order_queue"])
class OrderReceiver {

    @RabbitHandler
    fun receive(message: String){
        println("Receive: $message from order_queue, in OrderReceiver")
    }
}
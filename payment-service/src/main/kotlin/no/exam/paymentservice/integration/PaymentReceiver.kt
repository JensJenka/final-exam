package no.exam.paymentservice.integration

import no.exam.paymentservice.entity.PaymentEntity
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["payment_queue"])
class PaymentReceiver {

    @RabbitListener
    fun receive(message: String){
        println("Receive: $message from payment_queue")
    }
}
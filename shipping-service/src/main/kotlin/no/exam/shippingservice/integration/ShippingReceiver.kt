package no.exam.paymentservice.integration

import no.exam.shippingservice.service.ShippingService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["shipping_queue"])
class ShippingReceiver(@Autowired private val shippingService: ShippingService) {


    @RabbitHandler
    fun receivePaymentId(paymentId: String){
        println("Receive PaymentId?: " + paymentId)
        val shipping = shippingService.createShippingOnPaymentId(paymentId = paymentId.toString())
        println(shipping)
    }
}
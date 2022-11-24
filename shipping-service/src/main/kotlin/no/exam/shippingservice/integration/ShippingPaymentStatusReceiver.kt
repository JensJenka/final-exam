package no.exam.shippingservice.integration

import no.exam.shippingservice.service.ShippingService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["shipping_update_queue"])
class ShippingPaymentStatusReceiver(
    @Autowired private val shippingService: ShippingService,
    @Autowired private val rabbitTemplate: RabbitTemplate
) {

    @RabbitHandler
    fun receivePaymentId(paymentID: String){
        println("Payment id received: " + paymentID)
        val shippment = shippingService.flipPayedStatus(paymentID)
        rabbitTemplate.convertAndSend("order_shippable_queue", paymentID)
    }
}
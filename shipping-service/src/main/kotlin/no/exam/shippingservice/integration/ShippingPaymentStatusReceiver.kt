package no.exam.shippingservice.integration

import no.exam.shippingservice.service.ShippingService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["shipping_update_queue"])
class ShippingPaymentStatusReceiver(@Autowired private val shippingService: ShippingService) {

    @RabbitHandler
    fun receivePaymentId(paymentID: String){
        println("Payment id received: " + paymentID)
        val shippment = shippingService.flipPayedStatus(paymentID)
    }
}
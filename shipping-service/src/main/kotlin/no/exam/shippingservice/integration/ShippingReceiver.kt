package no.exam.shippingservice.integration

import no.exam.shippingservice.service.ShippingService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["shipping_queue"])
class ShippingReceiver(@Autowired private val shippingService: ShippingService) {


    @RabbitHandler
    fun receivePaymentId(paymentId: String){
        println("PaymentId received: " + paymentId + ", making shipping with it")
        val shipping = shippingService.createShippingOnPaymentId(paymentId = paymentId.toString())
    }
}
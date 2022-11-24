package no.exam.paymentservice.integration

import no.exam.paymentservice.service.PaymentService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["order_shippable_queue"])
class PaymentPayedReceiver(
    @Autowired private val rabbitTemplate: RabbitTemplate,
    @Autowired private val paymentService: PaymentService

) {
    @RabbitHandler
    fun receivePaymentIdFromShipping(paymentId: String){
        println("PaymentPayedReceiver: "+ paymentId)
        val orderId = paymentService.getOrderIdFromPaymentId(paymentId.toLong())
        rabbitTemplate.convertAndSend("order_update_shipping_queue", orderId.toString())
        println("OrderId to the PaymentId: " + orderId)


    }


}
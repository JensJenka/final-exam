package no.exam.paymentservice.integration

import no.exam.paymentservice.service.PaymentService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["payment_queue"])
class PaymentReceiver(@Autowired private val paymentService: PaymentService,
                      @Autowired private val rabbitTemplate: RabbitTemplate
) {
    @RabbitHandler
    fun receiveOrderID(orderId: String){
        println("Receive OrderId?: " + orderId)
        val payment = paymentService.createPaymentOnOrderID(orderId = orderId.toLong())
        rabbitTemplate.convertAndSend("shipping_queue", payment!!.paymentId.toString())
        println("Sent to shipping_queue ${payment.paymentId}")
    }
}
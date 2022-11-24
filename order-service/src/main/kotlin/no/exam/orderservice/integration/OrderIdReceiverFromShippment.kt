package no.exam.orderservice.integration

import no.exam.orderservice.service.OrderService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["order_update_shipping_queue"])
class OrderIdReceiverFromShippment(
    @Autowired private val rabbitTemplate: RabbitTemplate,
    @Autowired private val orderService: OrderService
){
    @RabbitHandler
    fun receiveOrderIdtoUpdateShippingReady(orderId: String){
        println("OrderId to make ready for shipping!: " + orderId)
        println("Order " + orderId + " is now shipped, check the mailbox everyday:3")
        orderService.makeShippable(orderId.toLong())
    }
}
package no.exam.orderservice.controller

import no.exam.orderservice.entity.OrderEntity
import no.exam.orderservice.exception.OrderNotFoundException
import no.exam.orderservice.integration.OrderSender
import no.exam.orderservice.integration.PaymentIntegrationService
import no.exam.orderservice.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.InvalidParameterException

@RestController
@RequestMapping("/api/order")
class OrderController(
    @Autowired private val orderService: OrderService,
    @Autowired private val orderSender: OrderSender,
    @Autowired private val paymentIntegrationService: PaymentIntegrationService
) {

    @GetMapping("/{id}") //gets an order
    fun getOrderOnId(@PathVariable id : Long?): ResponseEntity<OrderEntity>{
        id?.let {
            return ResponseEntity.ok(orderService.getOrderOnId(id))
        }
        throw OrderNotFoundException("There was an error locating your order")
    }

    @PostMapping("/newOrder") //creates a new order
    fun createOrder(@RequestBody orderEntity: OrderEntity): ResponseEntity<OrderEntity>{
        return ResponseEntity.ok(orderService.createOrder(orderEntity))
    }

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: Long?, @RequestBody orderEntity: OrderEntity?): ResponseEntity<OrderEntity?> {
        when {
            id == null -> throw InvalidParameterException()
            orderEntity == null -> InvalidParameterException()
            else -> {
                orderService.updateOrder(id, orderEntity)?.let {
                    return ResponseEntity.ok().body(it)
                }
            }
        }
        throw OrderNotFoundException("We could not find an order to update")
    }

    @DeleteMapping("/{id}/delete")
    fun deleteOrder(@PathVariable id: Long?) {
        id?.let {
            if(!orderService.deleteOrder(it)) throw OrderNotFoundException("Unable to delete order")
        }
    }



    @PostMapping("/{message}")
    fun createOrderMessage(@PathVariable message: String) {
        orderSender.sendMessage(message)
    }
    @PostMapping("/test/{id}")
    fun callPay(@PathVariable id: Long?) {
        paymentIntegrationService.sendHttpGETPayment(id.toString())
    }

    @PostMapping("http/callToPayment")
    fun callPaymentFromOrder() {
        paymentIntegrationService.sendHttpCallToPaymentService("This is a fucking string message")
    }
    @PostMapping("/http/testOrder") //creates a new order
    fun createTestOrder(@RequestBody orderEntity: OrderEntity): ResponseEntity<OrderEntity>{
        val newOrder = ResponseEntity.ok(orderService.createOrder(orderEntity))
        paymentIntegrationService.createPaymentHTTPrequest("${orderEntity.orderId}")
        return newOrder
    }
}
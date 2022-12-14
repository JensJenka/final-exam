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

    //Gets an order based on provided id - WORKS
    @GetMapping("/{id}") //gets an order
    fun getOrderOnId(@PathVariable id : Long?): ResponseEntity<OrderEntity>{
        id?.let {
            return ResponseEntity.ok(orderService.getOrderOnId(id))
        }
        throw OrderNotFoundException("There was an error locating your order")
    }

    //Updates an existing order - WORKS
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

    //Deletes an order - NOT WORKING
    @DeleteMapping("/http/delete/{id}")
    fun deleteOrder(@PathVariable id: Long?) {
        println("I delete mapping" + id)
        paymentIntegrationService.deletePaymentHTTP("$id")
        id?.let {
            //paymentIntegrationService.deletePaymentHTTP("{$id}")
            if(!orderService.deleteOrder(it)) throw OrderNotFoundException("Unable to delete order")
        }
    }

    //Fully integrated POST mapping, sends to payment and shippingservice
    @PostMapping("working/newOrder")
    fun createOrderAndSend(@RequestBody orderEntity: OrderEntity): ResponseEntity<OrderEntity>{
        val newOrder = ResponseEntity.ok(orderService.createOrder(orderEntity))
        orderSender.sendOrdIdToPayment(orderEntity.orderId.toString())
        return newOrder
    }

    //Sends a call to the Payment-service - WORKS
    @PostMapping("http/callToPayment")
    fun callPaymentFromOrder() {
        paymentIntegrationService.sendHttpCallToPaymentService("callToPayment: This is a fucking string message from the Order-Service!")
    }

    //Creates a new order, and payment based on the created order - WORKS
    @PostMapping("/http/newOrder") //creates a new order
    fun createTestOrder(@RequestBody orderEntity: OrderEntity): ResponseEntity<OrderEntity>{
        val newOrder = ResponseEntity.ok(orderService.createOrder(orderEntity))
        paymentIntegrationService.createPaymentHTTPrequest("${orderEntity.orderId}")
        return newOrder
    }
}
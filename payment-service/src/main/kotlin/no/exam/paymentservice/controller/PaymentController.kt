package no.exam.paymentservice.controller

import no.exam.paymentservice.entity.PaymentEntity
import no.exam.paymentservice.exception.PaymentNotFoundException
import no.exam.paymentservice.integration.PaymentSender
import no.exam.paymentservice.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payment")
class PaymentController(
    @Autowired private val paymentService: PaymentService,
    @Autowired private val paymentSender: PaymentSender
) {
    //Gets a payment on id
    @GetMapping("/{id}") //gets a payment on ID
    fun getPaymentOnId(@PathVariable id: Long?): ResponseEntity<PaymentEntity>{
        id?.let {
            return ResponseEntity.ok(paymentService.getPaymentOnId(id))
        }
        throw PaymentNotFoundException("There was an error locating your payment")
    }

    //Creates a new payment on an orderId
    @PostMapping("/newPayment")
    fun createPayment(@RequestBody paymentEntity: PaymentEntity): ResponseEntity<PaymentEntity>{
        return ResponseEntity.ok(paymentService.createPayment(paymentEntity))
    }

    //Sends the param of the request to the service itself(PaymentService)
    @GetMapping("/http/message/{message}")
    fun response(@PathVariable message: String): ResponseEntity<String> {
        println(message)
        return ResponseEntity.ok("Response from the PaymentController.kt")
    }

    //Experimental feature to say the least
    @DeleteMapping("http/delete/{id}")
    fun deletePaymentAfterOrderDelete(@PathVariable id: String): ResponseEntity<String> {
        println("Delete: " + id)
        paymentService.deletePaymentOnOrderId(id.toLong())
        return ResponseEntity.ok("ID DELETED! From: PaymentController, see Payment-run for message")
    }

    //Creates a new payment based on the orderId provided via rabbitMQ
    @GetMapping("/http/{orderId}")
    fun responseFromOrderService(@PathVariable orderId: String): ResponseEntity<String> {
        println("Create: " + orderId)
        paymentService.createPaymentOnOrderID(orderId.toLong())
        return ResponseEntity.ok("ID sent! From: PaymentController, see Payment-run for message")
    }

    //Integrated PUT, updates in all three services
    @PutMapping("/update/{paymentId}")
    fun updatePaymentSendQueueToShipping(@PathVariable paymentId: Long, @RequestBody paymentEntity: PaymentEntity): ResponseEntity<PaymentEntity>{
        val new = ResponseEntity.ok(paymentService.updatePayment(paymentId, paymentEntity))
        println("Send payment with id: " + paymentId.toString() + " to shippingService")
        paymentSender.sendPaymentUpdateToShippingService(paymentId.toString())
        return new
    }


}
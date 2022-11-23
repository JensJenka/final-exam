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
    @GetMapping("/{id}") //gets a payment on ID
    fun getPaymentOnId(@PathVariable id: Long?): ResponseEntity<PaymentEntity>{
        id?.let {
            return ResponseEntity.ok(paymentService.getPaymentOnId(id))
        }
        throw PaymentNotFoundException("There was an error locating your payment")
    }

    @GetMapping("/http/message/{message}")
    fun response(@PathVariable message: String): ResponseEntity<String> {
        println(message)
        return ResponseEntity.ok("Response from the PaymentController.kt")
    }

    @GetMapping("/http/{orderId}")
    fun responseFromOrderService(@PathVariable orderId: String): ResponseEntity<String> {
        println(orderId)
        paymentService.createPaymentOnOrderID(orderId.toLong())
        return ResponseEntity.ok("ID sent! From: PaymentController, see Payment-run for message")
    }


}
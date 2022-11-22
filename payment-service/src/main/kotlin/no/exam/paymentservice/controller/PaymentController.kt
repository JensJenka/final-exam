package no.exam.paymentservice.controller

import no.exam.paymentservice.entity.PaymentEntity
import no.exam.paymentservice.exception.PaymentNotFoundException
import no.exam.paymentservice.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payment")
class PaymentController(
    @Autowired private val paymentService: PaymentService
) {
    @GetMapping("/{id}") //gets an somethn
    fun getPaymentOnId(@PathVariable id: Long?): ResponseEntity<PaymentEntity>{
        id?.let {
            return ResponseEntity.ok(paymentService.getPaymentOnId(id))
        }
        throw PaymentNotFoundException("There was an error locating your payment")
    }
}
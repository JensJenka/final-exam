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
    @GetMapping("/{id}") //gets an somethn
    fun getPaymentOnId(@PathVariable id: Long?): ResponseEntity<PaymentEntity>{
        id?.let {
            return ResponseEntity.ok(paymentService.getPaymentOnId(id))
        }
        throw PaymentNotFoundException("There was an error locating your payment")
    }

    @PostMapping("/http/newPayment/{orderId}")
    fun createPayment(@PathVariable orderId:Long?,@RequestBody paymentEntity:PaymentEntity?):ResponseEntity<PaymentEntity?>{
        when{
            orderId == null -> throw PaymentNotFoundException("INSIDE WHEN")
                //paymentEntity?.paymentId!=null->throwPaymentNotFoundException("Thepaymentexists")
            else -> {
                if(paymentEntity != null){
                    paymentService.createPayment(orderId,paymentEntity)?.let{
                        return ResponseEntity.ok().body(it)
                    }
                }
            }
        }
        throw PaymentNotFoundException("Error occured during payment creation")
    }


    @PostMapping("/{message}")
    fun createPaymentMessage(@PathVariable message: String) {
        paymentSender.sendMessage(message)
    }

    @GetMapping("/http/{orderId}")
    fun responseToBankAccountService(@PathVariable orderId: String): ResponseEntity<String> {
        println(orderId)
        return ResponseEntity.ok("ID sent! From: PaymentController, see Payment-run for message")
    }

}
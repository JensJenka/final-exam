package no.exam.paymentservice.service

import no.exam.paymentservice.entity.PaymentEntity
import no.exam.paymentservice.repository.PaymentRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PaymentService(@Autowired private val paymentRepo: PaymentRepo) {

    fun getPaymentOnId(paymentId: Long): PaymentEntity{
        return paymentRepo.findById(paymentId).orElse(null)
    }
}
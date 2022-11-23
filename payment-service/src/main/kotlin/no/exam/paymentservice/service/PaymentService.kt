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

    fun createPaymentOnOrderID(orderId: Long) : PaymentEntity?{
        val newPayment = PaymentEntity(orderId = orderId)
        return paymentRepo.save(newPayment)
    }

    fun updatePayment(paymentId: Long, paymentEntity: PaymentEntity):PaymentEntity?{
        if(paymentRepo.existsById(paymentId)){
            paymentRepo.deleteById(paymentId)
            return paymentRepo.save(paymentEntity)
        }
        return null
    }

    fun deletePayment(paymentId:Long):Boolean{
        if(paymentRepo.existsById(paymentId)){
            paymentRepo.deleteById(paymentId)
            return true
        }
        return false
    }
}
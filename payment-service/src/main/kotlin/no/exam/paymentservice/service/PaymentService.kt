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

    fun createPayment(paymentEntity: PaymentEntity): PaymentEntity{
        return paymentRepo.save(paymentEntity)
    }

    fun createPaymentOnOrderID(orderId: Long) : PaymentEntity?{
        val newPayment = PaymentEntity(orderId = orderId)
        return paymentRepo.save(newPayment)
    }

    fun updatePayment(paymentId: Long, paymentEntity: PaymentEntity):PaymentEntity?{
        if(paymentRepo.existsById(paymentId)){
            val newPayment = PaymentEntity(paymentId, getOrderIdFromPaymentId(paymentId), paymentEntity.payed)
            return paymentRepo.save(newPayment)
        }
        return null
    }

    fun getOrderIdFromPaymentId(paymentId: Long): Long?{
        val payment = paymentRepo.getReferenceById(paymentId)
        println(payment.toString())
        return payment.orderId
    }




    fun deletePayment(paymentId:Long):Boolean{
        if(paymentRepo.existsById(paymentId)){
            paymentRepo.deleteById(paymentId)
            return true
        }
        return false
    }

    fun deletePaymentOnOrderId(orderId: Long):Boolean{
        if(paymentRepo.existsById(orderId)){
            paymentRepo.deleteById(orderId)
            return true
        }
        return false
    }
}
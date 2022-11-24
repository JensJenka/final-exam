package no.exam.shippingservice.service

import no.exam.shippingservice.entity.ShippingEntity
import no.exam.shippingservice.repo.ShippingRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ShippingService(@Autowired private val shippingRepo: ShippingRepo) {

    fun getShippingOnId(id: Long): ShippingEntity?{
        return shippingRepo.findById(id).orElse(null)
    }

    fun createShipping(shippingEntity: ShippingEntity): ShippingEntity{
        return shippingRepo.save(shippingEntity)
    }

    fun createShippingOnPaymentId(paymentId: String) : ShippingEntity?{
        println("IN SHIPIPING CREATE!")
        val newShipping = ShippingEntity(paymentId = paymentId.toLong())
        return shippingRepo.save(newShipping)
    }

    fun updateShipping(shippingId: Long, shippingEntity: ShippingEntity): ShippingEntity?{
        if (shippingRepo.existsById(shippingId)){
            shippingRepo.deleteById(shippingId)
            return shippingRepo.save(shippingEntity)
        }
        return null
    }

    fun deleteShipping(shippingId: Long): Boolean{
        if(shippingRepo.existsById(shippingId)){
            shippingRepo.deleteById(shippingId)
            return true
        }
        return false
    }

}
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
        val newShipping = ShippingEntity(paymentId = paymentId.toLong())
        return shippingRepo.save(newShipping)
    }

    fun flipPayedStatus(paymentId: String): ShippingEntity?{
        val shippingID = shippingRepo.findShippingEntitiesByPaymentId(paymentId.toLong()!!)
        println("Queried shippingId: " + shippingID + ", on paymentId: " + paymentId)
        val shippingEntity = getShippingOnId(shippingID)

        if (shippingEntity?.payed == false){
            updatePayedinShipping(shippingID, shippingEntity, true,true)
            println("The order is payed and registered as shippingReady: " + shippingEntity.payed)
            return shippingEntity
        }else if (shippingEntity?.payed == true){
            updatePayedinShipping(shippingID, shippingEntity, false, false)
            println("Update the payment to make it shippingReady: " + shippingEntity.payed)
            shippingEntity?.payed == false
            return shippingEntity
        }
        return shippingEntity
    }
    fun updatePayedinShipping(shippingId: Long, shippingEntity: ShippingEntity, payed: Boolean, shippingReady: Boolean): ShippingEntity?{
        if (shippingRepo.existsById(shippingId)){
            val newShipping = ShippingEntity(shippingId, shippingEntity.paymentId, payed, shippingReady)
            return shippingRepo.save(newShipping)
        }
        return null
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
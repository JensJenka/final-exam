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
}
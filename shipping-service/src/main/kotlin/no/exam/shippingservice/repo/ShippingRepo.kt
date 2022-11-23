package no.exam.shippingservice.repo

import no.exam.shippingservice.entity.ShippingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingRepo: JpaRepository<ShippingEntity, Long> {
}
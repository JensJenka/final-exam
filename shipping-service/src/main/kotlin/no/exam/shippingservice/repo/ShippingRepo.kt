package no.exam.shippingservice.repo

import no.exam.shippingservice.entity.ShippingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ShippingRepo: JpaRepository<ShippingEntity, Long> {

    @Query("SELECT shippingId FROM ShippingEntity WHERE paymentId = :paymentId")
    fun findShippingEntitiesByPaymentId(@Param("paymentId") paymentId: Long): Long

}
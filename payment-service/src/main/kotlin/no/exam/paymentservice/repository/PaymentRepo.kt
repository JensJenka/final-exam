package no.exam.paymentservice.repository

import no.exam.paymentservice.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepo: JpaRepository<PaymentEntity, Long> {
}
package no.exam.orderservice.repository

import no.exam.orderservice.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepo : JpaRepository<OrderEntity, Long> {
}
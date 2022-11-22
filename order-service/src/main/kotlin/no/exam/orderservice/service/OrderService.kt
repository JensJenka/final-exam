package no.exam.orderservice.service

import no.exam.orderservice.entity.OrderEntity
import no.exam.orderservice.repository.OrderRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired private val orderRepo: OrderRepo) {

    //Provide the id of an order and it should return
    fun getOrderOnId(orderId: Long): OrderEntity?{
        return orderRepo.findById(orderId).orElse(null)
    }

    //Post, with the required fields of the entity to create a new one
    fun createOrder(orderEntity: OrderEntity): OrderEntity{
        return orderRepo.save(orderEntity)
    }

    fun updateOrder(orderId: Long, orderEntity: OrderEntity): OrderEntity?{
        if(orderRepo.existsById(orderEntity.orderId)){
            return orderRepo.save(orderEntity)
        }
        return null
    }
}
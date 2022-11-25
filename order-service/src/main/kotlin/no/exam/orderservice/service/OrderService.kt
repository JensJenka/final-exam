package no.exam.orderservice.service

import no.exam.orderservice.entity.OrderEntity
import no.exam.orderservice.repository.OrderRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["orders"])
class OrderService(@Autowired private val orderRepo: OrderRepo) {

    @Cacheable(key = "#orderId")
    fun getOrderOnId(orderId: Long): OrderEntity?{
        return orderRepo.findById(orderId).orElse(null)
    }

    @CacheEvict(allEntries = true)
    fun createOrder(orderEntity: OrderEntity): OrderEntity{
        return orderRepo.save(orderEntity)
    }

    @CachePut(key = "#orderEntity.orderId")
    fun updateOrder(orderId: Long, orderEntity: OrderEntity): OrderEntity?{
        if(orderRepo.existsById(orderId)){
            orderRepo.deleteById(orderId)
            return orderRepo.save(orderEntity)
        }
        return null
    }

    fun makeShippable(orderId: Long): OrderEntity?{
        val change = getOrderOnId(orderId)
        val doneOrder = OrderEntity(orderId, change?.orderOwner, change?.orderAmount, true)
        return orderRepo.save(doneOrder)
    }

    @CacheEvict(key = "#orderId")
    fun deleteOrder(orderId: Long): Boolean{
        if(orderRepo.existsById(orderId)){
            orderRepo.deleteById(orderId)
            return true
        }
        return false
    }
}
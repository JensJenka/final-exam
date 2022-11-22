package no.exam.orderservice.controller

import no.exam.orderservice.entity.OrderEntity
import no.exam.orderservice.exception.OrderNotFoundException
import no.exam.orderservice.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/order")
class OrderController(
    @Autowired private val orderService: OrderService
) {

    @GetMapping("/{id}")
    fun getOrderOnId(@PathVariable id : Long?): ResponseEntity<OrderEntity>{
        id?.let {
            return ResponseEntity.ok(orderService.getOrderOnId(id))
        }
        throw OrderNotFoundException("There was an error locating your order")
    }
}
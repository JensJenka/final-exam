package no.exam.shippingservice.controller

import no.exam.shippingservice.service.ShippingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/shipping")
class ShippingController(
    @Autowired private val shippingService: ShippingService
) {

    @GetMapping()
    fun getHello(): ResponseEntity<String>{
        return ResponseEntity.ok("OPERATIVE SHIPPING")
    }
}
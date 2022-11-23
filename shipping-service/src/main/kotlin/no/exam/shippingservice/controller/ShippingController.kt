package no.exam.shippingservice.controller

import no.exam.shippingservice.entity.ShippingEntity
import no.exam.shippingservice.exception.ShippmentNotFoundException
import no.exam.shippingservice.service.ShippingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.InvalidParameterException

@RestController
@RequestMapping("/api/shipping")
class ShippingController(
    @Autowired private val shippingService: ShippingService
) {
    @GetMapping("/{id}")
    fun getShippingOnId(@PathVariable id: Long?): ResponseEntity<ShippingEntity>{
        if (id != null){
            return ResponseEntity.ok(shippingService.getShippingOnId(id))
        }
        throw ShippmentNotFoundException("There was an error in locating your shippment")
    }

    @PostMapping("/newShipping")
    fun createShippingAddress(@RequestBody shippingEntity: ShippingEntity): ResponseEntity<ShippingEntity>{
        return ResponseEntity.ok(shippingService.createShipping(shippingEntity))
    }

    @PutMapping("/{id}")
    fun updateShipping(@PathVariable id: Long?, @RequestBody shippingEntity: ShippingEntity?): ResponseEntity<ShippingEntity?> {
        when {
            id == null -> throw InvalidParameterException()
            else -> {
                shippingService.updateShipping(id, shippingEntity!!)?.let {
                    return ResponseEntity.ok().body(it)
                }
            }
        }
        throw ShippmentNotFoundException("We could not find a shiping address to update")
    }

    @DeleteMapping("/delete/{id}")
    fun deleteShipping(@PathVariable id: Long?){
        id?.let {
            if (!shippingService.deleteShipping(it)) throw ShippmentNotFoundException("Sorry, could not find shipping address to delete")
        }
    }

}
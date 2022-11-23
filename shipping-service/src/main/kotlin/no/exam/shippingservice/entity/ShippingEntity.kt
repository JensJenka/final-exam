package no.exam.shippingservice.entity

import javax.persistence.*

@Entity
@Table(name = "shipping_table")
class ShippingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_table_shipping_id_seq")
    @SequenceGenerator(
        name = "shipping_table_shipping_id_seq",
        allocationSize = 1
    )

    @Column(name = "shipping_id")
    val shippingId: Long? = null,

    @Column(name = "shipping_address")
    val shippingAddress: String,

    @Column(name = "payed")
    val payed: Boolean? = null,

    @Column(name = "shipping_ready")
    val shippingReady: Boolean? = null
){
    override fun toString(): String {
        return "ShippingEntity(shippingId=$shippingId, shippingAddress='$shippingAddress', payed=$payed, shippingReady=$shippingReady)"
    }
}

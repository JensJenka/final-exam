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

    @Column(name = "payment_id")
    val paymentId: Long? = null,

    @Column(name = "payed")
    val payed: Boolean? = false,

    @Column(name = "shipping_ready")
    val shippingReady: Boolean? = false
){

}

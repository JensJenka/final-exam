package no.exam.orderservice.entity

import javax.persistence.*

@Entity
@Table(name = "order_table")
class OrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_table_order_id_seq")
    @SequenceGenerator(
        name = "order_table_order_id_seq",
        allocationSize = 1
    )

    @Column(name = "order_id")
    val orderId: Long,

    @Column(name = "order_owner")
    val orderOwner: String,

    @Column(name = "order_amount")
    val orderAmount: Int,

    @Column(name = "shipping_ready")
    val shippingReady: Boolean? = null
){
    override fun toString(): String {
        return "OrderEntity(orderId=$orderId, orderOwner='$orderOwner', orderAmount=$orderAmount, shippingReady=$shippingReady)"
    }
}
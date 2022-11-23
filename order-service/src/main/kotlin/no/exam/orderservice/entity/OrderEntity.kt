package no.exam.orderservice.entity

import no.exam.orderservice.integration.PaymentIntegrationService
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
    val shippingReady: Boolean? = null,

    @OneToOne(fetch=FetchType.EAGER)
    @JoinTable(
    name="order_table_payment_table",
    joinColumns=[JoinColumn(name="payment_id")],
    inverseJoinColumns=[JoinColumn(name="order_id")]
    )private val payment: OrderEntity?
){
    override fun toString(): String {
        return "OrderEntity(orderId=$orderId, orderOwner='$orderOwner', orderAmount=$orderAmount, shippingReady=$shippingReady)"
    }
}
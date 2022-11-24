package no.exam.paymentservice.entity

import javax.persistence.*

@Entity
@Table(name = "payment_table")
class PaymentEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_table_order_id_seq")
    @SequenceGenerator(
        name = "payment_table_order_id_seq",
        allocationSize = 1
    )

    @Column(name = "payment_id")
    val paymentId: Long? = null,

    @Column(name = "order_id")
    val orderId: Long? = null,

    @Column(name = "payed")
    val payed: Boolean? = false,

) {
    override fun toString(): String {
        return "PaymentEntity(paymentId=$paymentId, orderId=$orderId, payed=$payed)"
    }
}


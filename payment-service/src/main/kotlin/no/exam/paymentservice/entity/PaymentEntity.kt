package no.exam.paymentservice.entity

import javax.persistence.*

@Entity
@Table(name = "payment_table")
class PaymentEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_order_id_seq")
    @SequenceGenerator(
        name = "payment_order_id_seq",
        allocationSize = 1
    )

    @Column(name = "payment_id")
    val paymentId: Long,

    @Column(name = "order_id")
    val orderId: Long,

    @Column(name = "payed")
    val payed: Boolean

) {
}
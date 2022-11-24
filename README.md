# Final-exam
## How To Use The Application
### Note the tables are empty on creation - Step 7. Will take care of that
1. Start docker containers in OrderService, then PaymentService and then ShippingService
2. Start DiscoveryServiceApplication
3. Start GatewayServiceApplication
4. Start OrderServiceApplication
5. Start PaymentServiceApplication
6. Start ShippingServiceApplication
7. POST to <http://localhost:8080/api/order/working/newOrder> with Block 1
#### Note you will see the Order_table populate with your POST-request, note that upon creation of an order a message is sent to the Payment-Service with that order_id. 
#### Which will populate the Payment_table with that order_id and corresponding payment_id as a new payment. 
#### The payment service will then send its paymentId to shippmentservice and a shippment will be created on that paymentId
#### All of this using RabbitMQ message queues sending the id's forwards
9. Then you can run all tests in OrderServiceApplicationTests!
10. 
### Order-Service API's have @Requestmapping("/api/order)
### Payment-Service API's have @Requestmapping("/api/payment)
| HTTP Request | URL end-point                                          |    Service    |                        Comment                        |
|--------------|:-------------------------------------------------------|:-------------:|:-----------------------------------------------------:|
| GET          | http://localhost:8080/api/order/1                      |     Order     |                  Get order on Id = 1                  |
| POST         | http://localhost:8080/api/order/newOrder               |     Order     |                     Copy Block 1                      |
| PUT          | http://localhost:8080/api/order/2                      |     Order     |  Updates order id = 2 and creates new, Copy Block 2   |
| DELETE       | http://localhost:8080/api/order/1/delete               |     Order     |                 NOT WORKING CORRECTLY                 |
| POST         | http://localhost:8080/api/order/testMSG                |     Order     |      Sends "testMSG"-param as message to itself       |
| POST         | http://localhost:8080/api/order/http/callToPayment     | Order/Payment |    Sends a hardcoded message from Order to Payment    |
| POST         | http://localhost:8080/api/order/http/newOrder          | Order/Payment |                     Copy Block 1                      |
| ---          | ---                                                    |      ---      |                         -----                         |
| GET          | http://localhost:8080/api/payment/1                    |    Payment    |                 Get payment on Id = 1                 |
| POST         | http://localhost:8080/api/payment/newPayment           |    Payment    |          Copy Block 3 (duplicates may occur)          |
| GET          | http://localhost:8080/api/payment/http/message/testMSG |    Payment    |      Sends "testMSG"-param as message to itself       |
| PUT          | http://localhost:8080/api/payment/update/3             |    Payment    |                     Copy Block 6                      |
| GET          | http://localhost:8080/api/payment/http/delete/1        |    Payment    |               Delete payment on Id = 1                |
| ---          | ---                                                    |      ---      |                         -----                         |
| GET          | http://localhost:8080/api/shipping/1                   |   Shipping    |                 Get payment on Id = 1                 |
| POST         | http://localhost:8080/api/shipping/newShipping         |   Shipping    |                     Copy Block 4                      |
| PUT          | http://localhost:8080/api/shipping/1                   |   Shipping    | Updates shipping id = 1 and creates new, Copy Block 5 |
| DELETE       | http://localhost:8080/api/shipping/delete/1            |   Shipping    |              Deletes shipping on id = 1               |


##### Block 1 into JSON body in HTTP-request
```json
{
  "orderOwner": "TEST POST",
  "orderAmount": 1000
}
```

##### Block 2 into JSON body in HTTP-request
```json
{
  "orderOwner": "TEST PUT",
  "orderAmount": 1000
}
```


##### Block 3 into JSON body in HTTP-request
```json
{
  "orderId": 1,
  "payed": "true"
}
```

##### Block 4 into JSON body in HTTP-request
```json
{
  "shippingAddress": "oslo POST",
  "payed": false,
  "shippingReady": false
}
```

##### Block 5 into JSON body in HTTP-request
```json
{
  "shippingAddress": "oslo PUT",
  "payed": false,
  "shippingReady": false
}
```
##### Block 6 into JSON body in HTTP-request
```json
{
  "payed": "true"
}
```
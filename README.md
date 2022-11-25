# Final-exam   -  Kandidatnummer: 1039
Note that my application lacks some tests, but I can reassure you that it excells if you use it as described below! :P \
•• \
What is my application based on?
Well, you submit an order to the online store(Step 8), that creates a payment, that creates a shipping.
When the payment is complete(Step 10), payment notifies shipping that payment is done. Which notifies payment of its shippability,
which again notifies order(online store) that the order is payed for and ready to be shipped to you!
###### What have I done?
• All 3 preliminary services with multiple endpoints all returning wrapped responses using ResponseEntity. \
• docker compose files that starts up databases.\
• RabbitMQ with predefined queues, and all preliminary services communicates through its message queues. \
• Gateway service who handles request over port 8080 and does the routing. It also uses the Discovery service (EUREKA) to discover them \
• I have implemented caching in the OrderService to showcase my abilities to do so. \
## How To Use The Application
### Note the tables are empty on creation - Step 8. Will take care of that
0. Start the rabbitMQ docker compose in root folder
1. Start docker compose in OrderService, then PaymentService and then ShippingService
2. Connect to PostgreSQL database with these variables:
   - POSTGRES_DB=localdevdb
   - POSTGRES_USER=localdevuser
   - POSTGRES_PASSWORD=pirate
3. Start DiscoveryServiceApplication
4. Start GatewayServiceApplication
5. Start OrderServiceApplication
6. Start PaymentServiceApplication
7. Start ShippingServiceApplication
8. POST to <http://localhost:8080/api/order/working/newOrder> with Block 1 (feel free to POST many times to populate the database sufficiently)
#### Note you will see the Order_table populate with your POST-request, note that upon creation of an order a message is sent to the Payment-Service with that order_id. 
#### Which will populate the Payment_table with that order_id and corresponding payment_id as a new payment. 
#### The payment service will then send its paymentId to shippmentservice and a shippment will be created on that paymentId
#### All of this using RabbitMQ message queues sending the id's forwards
9. Then you can run all tests in ApiTests! ( src/test/kotlin/no/exam/orderservice/integrationtest/ApiTests.kt )
10. PUT to <http://localhost:8080/api/payment/update/1> with Block 6 to update that Payment with id 1 to portray it paid.
#### Note that this will send a message to shippingservice and update payed to true, which will again update it shippingReady to true.
#### Shippmentservice will then send its paymentId back to paymentservice, which will send the paymentId's orderId to orderservice again.
#### Orderservice will then listen to check if any payments have come back payed and assert the order shippingReady.
11. Those are the key features of my program. 2 steps basically, create an order, and then update its payment, voila!


| HTTP Request | URL end-point                                          |        ServiceTransfers        |                                 Comment                                 |
|--------------|:-------------------------------------------------------|:------------------------------:|:-----------------------------------------------------------------------:|
| GET          | http://localhost:8080/api/order/1                      |             Order              |                           Get order on Id = 1                           |
| PUT          | http://localhost:8080/api/order/2                      |             Order              |          Updates order id = 2 and creates new ID, Copy Block 2          |
| DELETE       | http://localhost:8080/api/order/1/delete               |             Order              |                          NOT WORKING CORRECTLY                          |
| POST         | http://localhost:8080/api/order/working/newOrder       |     Order/Payment/Shipping     |                              Copy Block 1                               |
| POST         | http://localhost:8080/api/order/http/callToPayment     |         Order/Payment          |             Sends a hardcoded message from Order to Payment             |
| ---          | ---                                                    |              ---               |                                  -----                                  |
| GET          | http://localhost:8080/api/payment/1                    |            Payment             |                          Get payment on Id = 1                          |
| POST         | http://localhost:8080/api/payment/newPayment           |            Payment             |           Copy Block 3, making multiple payments on an order            |
| GET          | http://localhost:8080/api/payment/http/message/testMSG |            Payment             |               Sends "testMSG"-param as message to itself                |
| DELETE       | http://localhost:8080/api/payment/http/delete/1        |            Payment             |            Delete payment on Id = 1, order is left redundant            |
| PUT          | http://localhost:8080/api/payment/update/1             | Payment/Shipping/Payment/Order |                              Copy Block 6                               |
| ---          | ---                                                    |              ---               |                                  -----                                  |
| GET          | http://localhost:8080/api/shipping/1                   |            Shipping            |                         Get shipping on Id = 1                          |
| POST         | http://localhost:8080/api/shipping/newShipping         |            Shipping            |          Creates new shipping with no PaymentID, Copy Block 4           |
| PUT          | http://localhost:8080/api/shipping/1                   |            Shipping            | Updates shipping id = 1 and adds paymentId from JSON-body, Copy Block 5 |
| DELETE       | http://localhost:8080/api/shipping/delete/1            |            Shipping            |                       Deletes shipping on id = 1                        |


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
   "payed": false,
   "shippingReady": false
}
```

##### Block 5 into JSON body in HTTP-request
```json
{
   "paymentId": 5,
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
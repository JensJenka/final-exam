# final-exam
## How To Use The Application
### Note the tables are empty on creation - Step 7. Will take care of that
1. Start docker container
2. Start DiscoveryServiceApplication
3. Start GatewayServiceApplication
4. Start OrderServiceApplication
5. Start PaymentServiceApplication
6. Create a new sequence in the database called <payment_order_id_seq>
7. POST to <http://localhost:8080/api/order/http/newOrder> with Block 1
#### Note you will see the Order_table populate with your POST-request, note that upon creation of an order a message is sent to the Payment-Service with that order_id. Which will populate the Payment_table with that order_id and corresponding payment_id as a new payment.

### Order-Service API's have @Requestmapping("/api/order)
### Payment-Service API's have @Requestmapping("/api/payment)
| HTTP Request | URL end-point                                          |    Service    |                     Comment                     |
|--------------|:-------------------------------------------------------|:-------------:|:-----------------------------------------------:|
| GET          | http://localhost:8080/api/order/1                      |     Order     |               Get order on Id = 1               |
| POST         | http://localhost:8080/api/order/newOrder               |     Order     |                  Copy Block 1                   |
| PUT          | http://localhost:8080/api/order/2                      |     Order     |       Updates order id = 2, Copy Block 2        |
| DELETE       | http://localhost:8080/api/order/1/delete               |     Order     |             Delete order on Id = 1              |
| POST         | http://localhost:8080/api/order/testMSG                |     Order     |   Sends "testMSG"-param as message to itself    |
| POST         | http://localhost:8080/api/order/http/callToPayment     | Order/Payment | Sends a hardcoded message from Order to Payment |
| POST         | http://localhost:8080/api/order/http/newOrder          | Order/Payment |                  Copy Block 1                   |
| ---          | ---                                                    |      ---      |                      -----                      |
| GET          | http://localhost:8080/api/payment/1                    |    Payment    |              Get payment on Id = 1              |
| GET          | http://localhost:8080/api/payment/http/message/testMSG |    Payment    |   Sends "testMSG"-param as message to itself    |


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


VIDEOS TO POSSIBLY WATCH
spring cloud gateway filters tutorial
https://www.youtube.com/watch?v=PfzVE3AOhJ0
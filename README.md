# final-exam


### All API's have @Requestmapping("/api/order)
| HTTP Request | URL                                      |  End-point   | JSON body of HTTP-request |
|--------------|:-----------------------------------------|:------------:|:-------------------------:|
| GET          | http://localhost:8090/api/order/1        |    /{id}     |                           |
| POST         | http://localhost:8090/api/order/newOrder |  /newOrder   |       Copy Block 1        |
| PUT          | http://localhost:8090/api/order/9        |    /{id}     |       Copy Block 2        |
| DELETE       | http://localhost:8090/api/order/3/delete | /{id}/delete |                           |

There must be at least 3 dashes separating each header cell.
The outer pipes (|) are optional, and you don't need to make the
raw Markdown line up prettily. You can also use inline Markdown.

##### Block 1
```json
{
  "orderOwner": "TEST POST",
  "orderAmount": 1000,
  "shippingReady": "false"
}
```

##### Block 2
```json
{
  "orderOwner": "TEST PUT",
  "orderAmount": 1000,
  "shippingReady": false
}
```


VIDEOS TO POSSIBLY WATCH
spring cloud gateway filters tutorial
https://www.youtube.com/watch?v=PfzVE3AOhJ0
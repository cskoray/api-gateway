# Base API for the application

### http://localhost:8282/

### USER API 
```add user```

localhost:8082/v1/api/users/register

curl --location 'localhost:8082/v1/api/users/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "koray",
"email": "g@g.com",
"cardNumber": "1111222233334444",
"expiryDate": "0525",
"cvv": "275"
}'

```register a user to ZILCH```

localhost:8082/v1/api/users/zilch/register

curl --location 'localhost:8082/v1/api/users/zilch/register' \
--header 'Content-Type: application/json' \
--data '{
"userKey": "d29f1baf-81f0-4b96-8178-d9ab5d03b97d"
}'

### Product API - STORE
localhost:8082/v1/api/store/products

```add product```

curl --location 'localhost:8082/v1/api/store/products' \
--header 'Content-Type: application/json' \
--data '{
"name": "shoes",
"brand": "nike",
"price": "99.99"
}'

### ZILCH API 
```register a brand to ZILCH```

localhost:8082/v1/api/store/products/zilch/register

curl --location 'localhost:8082/v1/api/store/products/zilch/register' \
--header 'Content-Type: application/json' \
--data '{
"name": "nike"
}'

### ORDER API
```Order a product```

localhost:8082/v1/api/orders/order

curl --location 'localhost:8082/v1/api/orders/order' \
--header 'Content-Type: application/json' \
--data '{
"paymentToken": "5ab20180-2945-4755-9e3b-73eabd6c6589",
"paymentType": "ONE",
"merchantName": "Shopping Mall",
"productKeys":[
{
"productKey":"2bdbe2a8-d81d-48fe-a67a-335cd543b46d",
"productKey":"cd3fed50-66b0-4ba4-a1f3-67a7ed6966ce"
}
]
}'

### Actuator is enabled for health check and metrics
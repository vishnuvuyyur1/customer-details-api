# customer-details-api
  Technology stack
  - Java 11
  - Spring Boot
  - JPA
  - In Memory DB
  
# API Documentation
  Base URL: http://localhost:8080/api/v1
  Operations:
  
  |No| Operation | Endpoint | Method
|----|---|---|---|
|1| create customer  |/customers| POST |
|2| get all customers | /customers | GET |
|3| get customer by identifier |/customers/{id} |GET | 
|4| get customers by first name |/customers?firstName=abc | GET |
|5| Update customer address  | /customers/{id}/address | PUT |

## create customer

Request 
  |Attributes|Type|Validation | Required | Method
|----|---|---|---|---|
|firstName|string | max 10 chars| yes| POST |
|lastName|string | max 10 chars| yes | GET |
|age|number |min 1 max 150 (num)|yes|GET | 
|addresses|Address | min 1 |yes | GET |
```
{
	"firstName": "hello",
	"lastName": "world",
	"age": 10,
	"addresses": [{
		"address": "333 john doe st",
		"city": "Brussels",
		"state": "North Belgium",
		"country": "Belgium",
		"zipCode": "5443351",
		"addressType": "LIVING"
	}]
}
```
Response 
 |Attributes|Type|
|----|---|
|statusCode|int | 
|message|string | 
|id|long | 
|firstName|string | 
|lastName|string | 
|age|number | 
|addresses|Address |

```
{
	"statusCode": 200,
	"message": "Success",
	"data": {
		"id": 1,
		"firstName": "hello",
		"lastName": "world",
		"age": 10,
		"addresses": [{
			"id": 2,
			"address": "333 john doe st",
			"city": "Brussels",
			"state": "North Belgium",
			"zipCode": "5443351",
			"country": "Belgium",
			"addressType": "LIVING"
		}]
	}
}
```

## get all customers
Request
None

Response


# customer-details-api
  Technology stack
  - Java 11
  - Spring Boot
  - JPA
  - In Memory DB
  - Maven Build
  - Environment: Embedded Tomcat container 
  
# API Documentation
  Base URL: http://localhost:8080/management/api/v1 <br>
  Operations:
  
  |No| Operation | Endpoint | Method
|----|---|---|---|
|1| create customer  |/customers| POST |
|2| get all customers | /customers | GET |
|3| get customer by identifier |/customers/{id} |GET | 
|4| get customers by first name |/customers?firstName=abc | GET |
|5| Update customer address  | /customers/{id}/address | PUT |

## 1. create customer
- URI: /customers
- Method: Post
<br>
Request Body

  |Attributes|Type|Validation | Required |
|----|---|---|---|
|firstName|string | max 10 chars| yes|
|lastName|string | max 10 chars| yes |
|age|number |min 1 max 150 (num)|yes| 
|addresses|Address | min 1 |yes |

Address Type table is available below at section 5. update customer address
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

## 2. get all customers
 - URI: /customers
 - Method: GET

Request Body <br>
None

Response
```
{
    "statusCode": 200,
    "message": "Success",
    "data": [
        {
            "id": 1,
            "firstName": "hello",
            "lastName": "world",
            "age": 10,
            "addresses": [
                {
                    "id": 2,
                    "address": "333 john doe st",
                    "city": "Brussels",
                    "state": "North Belgium",
                    "zipCode": "5443351",
                    "country": "Belgium",
                    "addressType": "LIVING"
                }
            ]
        }
    ]
}
```
## 3. get customer by identifier
 - URI: /customers/{id}
 - Method: GET

Request Body <br>
None

Response
```
{
    "statusCode": 200,
    "message": "Success",
    "data": {
        "id": 1,
        "firstName": "hello",
        "lastName": "world",
        "age": 10,
        "addresses": [
            {
                "id": 2,
                "address": "333 john doe st",
                "city": "Brussels",
                "state": "North Belgium",
                "zipCode": "5443351",
                "country": "Belgium",
                "addressType": "LIVING"
            }
        ]
    }
}
```
## 4. get customers by first name
URI: /customers?firstName={value}
Method: GET

Request Body <br>
None

Response
```
{
    "statusCode": 200,
    "message": "Success",
    "data": [
        {
            "id": 1,
            "firstName": "hello",
            "lastName": "world",
            "age": 10,
            "addresses": [
                {
                    "id": 2,
                    "address": "333 john doe st",
                    "city": "Brussels",
                    "state": "North Belgium",
                    "zipCode": "5443351",
                    "country": "Belgium",
                    "addressType": "LIVING"
                }
            ]
        }
    ]
}
```
## 5. Update Customer address
URI: /customers/{id}/address
Method: PUT

Request Body : Address Type
  |Attributes|Type|Validation | Required |
|----|---|---|---|
|address|string | max 50 chars| yes| 
|city|string | max 20 chars| yes |
|state/province|number |max 20 chars|yes|
|country|string | max 20chars |yes |
|zipCode|string | max 10 chars |yes |
|addressType|enum |LIVING/SHIPPING/BILLING/OFFICE/BRANCH |yes |

```
 {
 	"address": "444 john doe st",
 	"city": "Brussels",
 	"state": "North Belgium",
 	"country": "Belgium",
 	"zipCode": "5443351",
 	"addressType": "LIVING"
 }
```
Response
```
{
    "statusCode": 200,
    "message": "Success",
    "data": {
        "id": 1,
        "firstName": "hello",
        "lastName": "world",
        "age": 10,
        "addresses": [
            {
                "id": 2,
                "address": "444 john doe st",
                "city": "Brussels",
                "state": "North Belgium",
                "zipCode": "5443351",
                "country": "Belgium",
                "addressType": "LIVING"
            }
        ]
    }
}
```
# Next steps
Fueatures to add
- swagger documentation
- More test cases. Added minimum test cases for demo purpose.
- Securing the API.
- Improvised exception handling with codes.
- UI for customer to interact for entering details.

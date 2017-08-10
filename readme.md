## Spring Data REST - PUT doesn't work since v.2.5.7-RELEASE

### many-to-one

```java
@Entity
public class Person extends BaseEntity {
    
    private String name;
    
    @ManyToOne
    private Address address;
}

public class Address extends BaseEntity {
    
    private String street;
}
```
    PUT http://localhost:8080/api/persons/1

Request body:
```json
{
	"name": "person1u",
	"address": "http://localhost:8080/api/addresses/2"
}
```
- **v.2.5.6:** Response:

```json
{
    "name": "person1u",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/persons/1"
        },
        "person": {
            "href": "http://localhost:8080/api/persons/1"
        },
        "address": {
            "href": "http://localhost:8080/api/persons/1/address"
        }
    }
}
``` 

- **v.2.5.7:** Response:

```json
{
    "cause": {
        "cause": {
            "cause": null,
            "message": "Can not construct instance of io.github.cepr0.putissue.manytoone.Address: no String-argument constructor/factory method to deserialize from String value ('http://localhost:8080/api/addresses/2')\n at [Source: N/A; line: -1, column: -1] (through reference chain: io.github.cepr0.putissue.manytoone.Person[\"address\"])"
        },
        "message": "Could not read payload!; nested exception is com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of io.github.cepr0.putissue.manytoone.Address: no String-argument constructor/factory method to deserialize from String value ('http://localhost:8080/api/addresses/2')\n at [Source: N/A; line: -1, column: -1] (through reference chain: io.github.cepr0.putissue.manytoone.Person[\"address\"])"
    },
    "message": "Could not read an object of type class io.github.cepr0.putissue.manytoone.Person from the request!; nested exception is org.springframework.http.converter.HttpMessageNotReadableException: Could not read payload!; nested exception is com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of io.github.cepr0.putissue.manytoone.Address: no String-argument constructor/factory method to deserialize from String value ('http://localhost:8080/api/addresses/2')\n at [Source: N/A; line: -1, column: -1] (through reference chain: io.github.cepr0.putissue.manytoone.Person[\"address\"])"
}
```
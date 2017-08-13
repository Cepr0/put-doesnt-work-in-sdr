## Spring Data REST - PUT request does not work properly since v.2.5.7!

Since version 2.5.7 Spring Data REST does not properly perform a **PUT** request
to update resource which has associated resources. Unlike PATCH request that works as expected!

For example, `Person` has a many-to-one association with `Addres`. If we perform
a PUT request with SDR v.2.5.6 (Spring Boot v.1.4.3) then all works OK. But if we switch
to version 2.5.7 (i.e. to Spring Boot v.1.4.4) then we get an error:

```Can not construct instance of Address: no String-argument constructor/factory method to deserialize from String value```

The same happens with other types of associations, for example with one-to-many (uni- and bidirectional) - 
see the application code and tests.

This problem is present in all versions of the Spring Boot since 1.4.4 including the latest stable 1.5.6 version, 
as well as the newest 2.0.0-SNAPSHOT version.

To work around this situation we can just switch to SDR v.2.5.6 (Spring Boot v.1.4.3).

### UPDATE #1



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
- **v.2.5.6** Response:

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

- **v.2.5.7** Response:

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
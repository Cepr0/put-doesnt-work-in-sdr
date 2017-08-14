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

I found how to avoid the error `Can not construct instance of Address: no String-argument constructor/factory
method to deserialize from String value`. Since I'm using [Lombok](https://projectlombok.org/) in this project,
it is necessary to tell Lombok to suppress using the `@ConstructorProperties` annotation in 
[generated constructors](https://projectlombok.org/features/constructor).
So I set `lombok.anyConstructor.suppressConstructorProperties=true` in the 'lombok.config' file and the error was gone.

But a new problem was found - PUT request does not update associated objects at all! 
The example below is demonstrating this behavior. We are trying to update Person by changing his 
Address from `address/1` (initial value) to `address/2` - then it remains the same: `address/1`:      

```java
@Entity
public class Person extends BaseEntity {
    
    private String name;
    
    @ManyToOne
    private Address address;

    // other stuff
}

@Entity
public class Address extends BaseEntity {
    
    private String street;
    
    // other stuff    
}
```

Trying to update Person: 

    PUT http://localhost:8080/api/persons/1

```json
{
	"name": "person1u",
	"address": "http://localhost:8080/api/addresses/2"
}
```

Getting the correct response:

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

And checking for a 'new' Address of Person - address was not updated: 

    GET http://localhost:8080/api/persons/1/address

```json
{
    "street": "address1",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/addresses/1"
        },
        "address": {
            "href": "http://localhost:8080/api/addresses/1"
        }
    }
}
```

(The PATCH request still works as expected)
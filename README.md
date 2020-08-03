# spring-hateoas-demo

This application is a simple demo for using spring hateoas library functions.

### Response JSON

``` json
{
    "_embedded": {
        "users": [
            {
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/users/007e6f5f-1bc2-4141-a2f0-046d242f88b9"
                    }
                },
                "id": "007e6f5f-1bc2-4141-a2f0-046d242f88b9"
            }
        ]
    },
    "_links": {
        "first": {
            "href": "http://localhost:8080/users?page=0&size=1"
        },
        "last": {
            "href": "http://localhost:8080/users?page=1000&size=1"
        },
        "next": {
            "href": "http://localhost:8080/users?page=1&size=1"
        },
        "self": {
            "href": "http://localhost:8080/users?page=0&size=1"
        }
    },
    "page": {
        "number": 0,
        "size": 1,
        "totalElements": 1001,
        "totalPages": 1001
    }
}
```

### Models

All endpoint objects, commonly call DTO, must extend the `RepresentationModel<>` class. By default, when serializing 
 the object into the `_embedded` object, spring will use the class name, to change to something more readable you can use
 the `@Relation` annotation. In the [UserModel](/src/main/java/io/opelt/hateoas/controller/UserModel.java) example,
 this changes the object from: 
``` json
{
    "_embedded": {
        "userModel": {}
    }
}
```
To:
``` json
{
    "_embedded": {
        "user": {}
    }
}
```

 To create the object you must use an `RepresentationModelAssembler`, a mapper class for assembling a model from an
  entity. In the [UserModelAssembler](/src/main/java/io/opelt/hateoas/controller/UserModelAssembler.java) this is done by
  extending the class `RepresenatinModelAssemblerSupport` and overriding the method `toModel`. Using the parent method 
  `createWithId`, the model will be created with the `self` link.
  
## Paginated responses

For paginated endpoints you just need to inject the `PagedResourcesAssembler<>`. This way the links for other pages,
first, previous, next, last and self, will be auto generated.
   
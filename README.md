# spring-boot-multitenant-mongodb

This project contains a spring-data-mongodb factory extension which is responsible to choose the database name at runtime.

For each request made by a user, Spring data asks the mongodb factory which database may use. This extension uses a `TenantProvider` interface to decide the database name at runtime.


## Setting up your application

Enable Spring boot auto configuration using the`@EnableAutoConfiguration` annotation.

```java
@SpringBootApplication
@EnableAutoConfiguration
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
    
}
```

Create a TenantProvider implementation:

```java
@Component
public class MyTenantResolver implements TenantResolver {

    @Override
    public String resolve(HttpServletRequest request) {
        return request.getHeader("X-Tenant"); // implement your own strategy
    }

}
```

## Default behavior

Considering the following application.properties file:
```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=sample
```

The default database name is `sample`, so if the `TenantProvider` implementation returns `tenant1` as result, the `MultitenantMongoDbFactory` will consider the database name as `sample-tenant1`.

You can also override the database prefix by  providing a tenant prefix as a  property:

```properties
spring.data.mongodb.tenant-prefix=my-prefix
```

When `MultitenantMongoDbFactory` finds this property, the result is `my-prefix-tenant1` instead of `sample-tenant1`.


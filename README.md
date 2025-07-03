# Spring Boot Security

- Step 1 - Default login page - localhost:8080
- Step 2 - Default username - user
- Step 3 - Default password will be generated on console

Spring Security is a framework that provides **Authentication**, **Authorization** & **Protection against common attacks**.

### Authentication
#### Authentication is how we verify the identity of who is trying to access a particular resource.
- A common way to authenticate users is by requiring the user to enter a username & password. Once authentication is performed, we know the identity & can perform authorization. 
- Spring Security provides built-in support for authenticating users & it provides generic authentication support that applies in both Servlet & WebFlex environment.

### Authorization
#### Authorization is determining who is allowed to access a particular resource.
Spring Security provides defense in depth by allowing for request based authorization & method based authorization.

1. Request Based Authorization
    - Spring Security provides authorization based upon the request for both Servlet & WebFlux environments.
2. Method Based Authorization
    - Spring Security provides authorization based on the method invocation for both Servlet & WebFlux environments.



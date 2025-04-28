# :closed_lock_with_key: Spring Security + JWT

This is a basic Spring Boot API that uses Spring Security and Json Web Token for authorization and authentication. It's built with MVC three layered architecture and uses H2 for persistance.  

It has the JWT Authorization filter and th JWT Authentication filter and the authentication manager uses de user services directly.

Has 2 endpoints:
- user/all: which is allowed for unregistered users
- user/{id}: which is allowed only for registered users

Tested with postman.

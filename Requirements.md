## Functional Requirements

- Create a service using your Favorite Framework (Spring)
- Make a call to the following API
  curl --location --request GET 'https://fakerapi.it/api/v1/books?_quantity=1'
- Get the response parse and same and validate
- Validation
- Check for empty response
- Check for any HTTP error
- Put the response in some class
- Use this Entity to update it in DB (Use Hibernate/JPA as ORM)
- Create a DB connection
- Create tables using migration via Code (flyway spring)
- Run that migration
- Use the above response to update in DB, the following data
  all attributes from the response

- Writing unit test case for Controller,Service and repository
- Create a health checkapi and manage the version via application.properties/yaml

## Libraries and tools

- Java
- Spring boot/Dropwizard
- Hibernate/JPA => ORM
- Flyway => Migration
- Unit Testing => Junit
- Mocking => Mockito
- Jackson (For Parsing) using Object Mapper
- Swagger(OPen API) For API Documentation

# @name@

This is a shared library which goal is to standardize the exception handler.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Using the Library

Add the following Maven dependency:

```xml
<dependencies>

    <dependency>
        <groupId>@project.groupId@</groupId>
        <artifactId>@project.artifactId@</artifactId>
        <version>@project.version@</version>
    </dependency>

</dependencies>
```

#### Handling errors on Spring Rest controllers

This library provides following exceptions:

|       Exception      |                                                                                                                              Description                                                                                                                             |
|:--------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| BusinessException    | Represents an error related to the business itself. Generally used when the failure is a consequence of user interaction (invalid user inputs, malformed data), and thus, recoverable without demanding system administrators intervention.                          |
| IntegrationException | Represents an error related to the infrastructure of services. Generally used when the failure is a consequence of other dependent systems malfunction, and thus, there's nothing the user can do to recover from. System's administrators intervention is required. |

This library also provides *ExceptionHandlers* to those exceptions, which can be enabled by annotating the `SpringBootApplication` class with `@EnableControllerExceptionHandling`.

* `BusinessException` is translated into response with status 400 (bad request) with a body containing _code_ and _message_ fields.
* `IntegrationException` is translated into response with status 500 (internal server error) with a body containing _code_ and _message_ fields. The message field does not reveal to the user the details about the error, given its not recoverable by the user.

The exception handlers require a message source (message.properties file) present on classpath. For `BusinessException`, message keys must match the `placeholder` value from the exception instance. For `IntegrationException`, message key is fixed: _exception.integration_.

Sample message.properties file:

```
exception.integration=Integration failure
exception.business.invalid-document=The document informed is invalid
```

## Running the tests

As a developer, it is necessary to run the tests whenever modifications are made in the source code. To run the tests, simple execute:

```
mvn clean test
```

In order to run just unit tests, skipping integration tests, do:

```
mvn clean test -DexcludedGroups=integration
```

### Test Coverage

This project uses JaCoCo to keep track of the code test coverage. Reports about the test coverage can be found on `target/site/jacoco/index.html`.

## Deployment

```
TBD
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

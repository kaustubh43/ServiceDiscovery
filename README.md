# ServiceDiscovery — Eureka Server

A Spring Boot-based **Netflix Eureka Server** that provides service registration and discovery for the ecommerce microservices ecosystem.

## Overview

This service acts as the central service registry. Other microservices register themselves here and use it to discover and communicate with each other.

| Property | Value |
|---|---|
| **Framework** | Spring Boot 3.5.7 |
| **Java** | 17 |
| **Port** | 8761 |
| **Eureka Dashboard** | `http://localhost:8761` |

## Prerequisites

- **Java 17** or later
- **Maven 3.9+** (or use the included Maven Wrapper)

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd ServiceDiscovery
```

### Build

```bash
./mvnw clean package
```

On Windows:

```cmd
mvnw.cmd clean package
```

### Run

```bash
./mvnw spring-boot:run
```

Or run the packaged JAR:

```bash
java -jar target/ServiceDiscovery-0.0.1-SNAPSHOT.jar
```

The Eureka dashboard will be available at **http://localhost:8761**.

### Run Tests

```bash
./mvnw test
```

## Configuration

Key properties in `src/main/resources/application.properties`:

```properties
spring.application.name=ServiceDiscovery
server.port=8761
eureka.instance.hostname=localhost
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

- **`register-with-eureka=false`** — Prevents the server from registering itself as a client.
- **`fetch-registry=false`** — The server does not need to fetch the registry since it *is* the registry.

## Registering a Client Service

Add the Eureka Client dependency to your microservice and configure it to point to this server:

```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

## Maven Wrapper

This project includes the Maven Wrapper so contributors don't need a local Maven installation. If the wrapper files are missing, regenerate them:

```bash
mvn -N wrapper:wrapper
```

Then commit the generated files:

```bash
git add mvnw mvnw.cmd .mvn/wrapper/maven-wrapper.jar .mvn/wrapper/maven-wrapper.properties
git commit -m "Add Maven wrapper files"
git push
```

## Project Structure

```
ServiceDiscovery/
├── .mvn/wrapper/             # Maven Wrapper config
├── src/
│   ├── main/
│   │   ├── java/             # Application source code
│   │   └── resources/        # Configuration files
│   └── test/
│       └── java/             # Test source code
├── mvnw                      # Maven Wrapper (Unix)
├── mvnw.cmd                  # Maven Wrapper (Windows)
├── pom.xml                   # Maven build descriptor
└── README.md
```

## License

See [pom.xml](pom.xml) for license details.

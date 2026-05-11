# Order Service

Order Service is part of the Shopping Platform Microservices project.

It is responsible for creating orders, publishing order creation events to Kafka, consuming inventory reservation results, and updating order status based on the result.

## Responsibilities

- Create new orders
- Store orders in PostgreSQL
- Save new orders with an initial `PENDING` status
- Publish `OrderCreatedEvent` to Kafka
- Consume `InventoryReservationResultEvent` from Kafka
- Update order status to `CONFIRMED` or `REJECTED`

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Kafka
- PostgreSQL
- Apache Kafka
- Docker / Docker Compose
- Maven

## Project Structure

```text
order_service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── infrastructure/
│       └── docker-compose.yml   # Kafka and Kafka UI compose file
├── docker-compose.yml           # Order Service / database compose file
├── Dockerfile
├── pom.xml
├── .env.example
├── application-local.example.properties
└── README.md
```

## Kafka Event Flow

```text
Order Service
  ├── publishes OrderCreatedEvent
  │       ↓
  │     Kafka topic: order-created
  │       ↓
  └── consumes InventoryReservationResultEvent
          ↑
        Kafka topic: inventory-reservation-result
```

## Kafka Topics

| Topic | Direction | Event |
|---|---|---|
| `order-created` | Produced by Order Service | `OrderCreatedEvent` |
| `inventory-reservation-result` | Consumed by Order Service | `InventoryReservationResultEvent` |

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/orders` | Create a new order |

Example request:

```http
POST http://localhost:8081/orders
Content-Type: application/json
```

```json
{
    "productCode": "ps5",
    "requestedQuantity": 2,
    "customerId": 9090
}
```

## Running the Service Locally

This is useful when you want to run the Spring Boot application from IntelliJ or Maven.

### 1. Create local application properties

Copy the example local properties file:

```bash
cp src/main/resources/application-local.example.properties src/main/resources/application-local.properties
```

On Windows PowerShell:

```powershell
Copy-Item src/main/resources/application-local.example.properties src/main/resources/application-local.properties
```

Then edit:

```text
src/main/resources/application-local.properties
```

Set your local database and Kafka values.

Typical local Kafka setting:

```properties
spring.kafka.bootstrap-servers=localhost:9094
```

The `application-local.properties` file should not be committed to Git. It is meant for your local machine only.

### 2. Start the Order Service database

From the root of this repository:

```bash
docker compose up -d
```

This starts the PostgreSQL database used by Order Service.

If the service-specific `docker-compose.yml` also starts the Order Service container and you only want the database, run the database service by name instead:

```bash
docker compose up -d order_db
```

Use the actual database service name from your `docker-compose.yml` if it is different.

### 3. Start Kafka and Kafka UI

Kafka and Kafka UI are located in:

```text
src/infrastructure/docker-compose.yml
```

Start them with:

```bash
docker compose -f src/infrastructure/docker-compose.yml up -d
```

Kafka UI should then be available at:

```text
http://localhost:8090
```

### 4. Run the Spring Boot application locally

Using Maven:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

Or run the application from IntelliJ with the `local` profile enabled.

The service should start on:

```text
http://localhost:8081
```

## Running with Docker Compose

The standard Docker Compose setup can be started from the root of this repository:

```bash
docker compose up --build
```

This uses the repository's `docker-compose.yml`.

To stop it:

```bash
docker compose down
```

To stop it and remove volumes:

```bash
docker compose down -v
```

## Running as Part of the Full Shopping Platform

For running the complete system, use the root Shopping Platform repository.

The full system includes:

- API Gateway
- Order Service
- Inventory Service
- PostgreSQL databases
- Kafka
- Kafka UI

When running inside the full Docker Compose setup, this service should connect to Kafka using the Docker network hostname:

```properties
spring.kafka.bootstrap-servers=kafka:9092
```

When running locally from IntelliJ, it should usually connect to Kafka through:

```properties
spring.kafka.bootstrap-servers=localhost:9094
```

## Environment Variables

This repository includes:

```text
.env.example
```

Create a local `.env` file from it:

```bash
cp .env.example .env
```

On Windows PowerShell:

```powershell
Copy-Item .env.example .env
```

Then adjust the database credentials if needed.

The `.env` file should not be committed.

## Order Status Lifecycle

```text
PENDING
  ↓
CONFIRMED   if inventory reservation succeeds

PENDING
  ↓
REJECTED    if inventory reservation fails
```

## Notes

- Order Service owns its own database.
- It should not directly access the Inventory Service database.
- Communication with Inventory Service happens asynchronously through Kafka.
- Kafka topics can be created manually or through Spring Kafka `NewTopic` beans.
- For full-system execution, prefer the root Shopping Platform Docker Compose file.

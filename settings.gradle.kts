rootProject.name = "ticketing-system"


include(
    ":platform:eureka-server",
    ":platform:admin-server",
    ":business:a-ticketing-service",
    ":business:b-order-service",
    ":business:c-reporting-service",
)
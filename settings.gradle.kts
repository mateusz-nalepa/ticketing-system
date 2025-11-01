rootProject.name = "ticketing-system"


include(
    ":platform:eureka-server",
    ":platform:admin-server",
    ":business:ticketing-service",
    ":business:order-service",
    ":business:reporting-service",
)
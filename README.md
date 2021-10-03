The service can be running by executing `./mvnw spring-boot:run`
The rebalancing runs once per day by invoking `com.nchechin.portfolio.rebalancing.RebalancingService.rebalancePortfolio` with Spring scheduled task support.
Another invocation option (endpoint call, message, etc...) can be implemented if needed.
In order to test the service end-to-end `com.nchechin.portfolio.rebalancing.RebalancingServiceIntegrationTest` can be executed.
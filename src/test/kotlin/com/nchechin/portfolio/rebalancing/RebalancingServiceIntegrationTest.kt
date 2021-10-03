package com.nchechin.portfolio.rebalancing

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * An integration test to check the rebalancing flow end to end
 * using wiremock service to emulate financial portfolio service
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RebalancingServiceIntegrationTest {

    @Autowired
    lateinit var rebalancingService: RebalancingService

    @BeforeAll
    fun setup() {
        val wireMockServer = WireMockServer(WireMockConfiguration.options().port(8081))
        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/financial_portfolio_service/customer/1"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            """
            {
                "customerId": 1,
                "stocks": 6700,
                "bonds": 1200,
                "cash": 400
            }
        """.trimIndent()
                        )
                )
        )
        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/financial_portfolio_service/customer/2"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            """
            {
                "customerId": 2,
                "stocks": 5000,
                "bonds": 200,
                "cash": 1300
            }
        """.trimIndent()
                        )
                )
        )
        wireMockServer.stubFor(
            WireMock.post(WireMock.urlEqualTo("/financial_portfolio_service/execute"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(201)
                )
        )
        wireMockServer.start()
    }

    @Test
    fun `portfolio rebalancing succeeded`() {
        rebalancingService.rebalancePortfolio()
    }

}
package com.nchechin.portfolio.rebalancing

import com.nchechin.portfolio.rebalancing.domain.CustomerDetails
import com.nchechin.portfolio.rebalancing.domain.Trade
import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FinancialPortfolioServiceClient(
    val financialPortfolioServiceFeignClient: FinancialPortfolioServiceFeignClient
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getCustomerDetailsById(id: Long): CustomerDetails? = try {
        financialPortfolioServiceFeignClient.getCustomerDetailsById(id)
    } catch (ex: FeignException) {
        if (ex.status() == 404) {
            logger.error("No details found for customer $id in portfolio service. Skipping rebalancing.")
            null
        } else throw ex
    }

    fun executeTrades(trades: List<Trade>) =
        financialPortfolioServiceFeignClient.executeTrades(trades)

}
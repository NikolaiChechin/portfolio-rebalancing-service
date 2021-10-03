package com.nchechin.portfolio.rebalancing

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class RebalancingService(
    val csvReaderService: CsvReaderService,
    val tradeService: TradeService,
    val financialPortfolioServiceClient: FinancialPortfolioServiceClient
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    val batchSize = 3

    //The rebalancing can be running every day at 8am for example
    @Scheduled(cron = "0 0 8 * * *")
    fun rebalancePortfolio() {
        logger.info("Starting portfolio rebalancing with batch size $batchSize")
        csvReaderService
            .readCustomers()
            .map { customer ->
                val customerDetails = financialPortfolioServiceClient.getCustomerDetailsById(customer.customerId)
                Pair(customer, customerDetails)
            }
            .mapNotNull {
                val customerDetails = it.second
                if (customerDetails != null) Pair(it.first, customerDetails) else null
            }
            .map {
                tradeService.getTradeForCustomer(it.first, it.second)
            }
            .chunked(batchSize)
            .forEach {
                logger.info("Updating customers portfolio with trades: $it")
                financialPortfolioServiceClient.executeTrades(it)
            }

    }

}
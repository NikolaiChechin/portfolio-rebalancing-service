package com.nchechin.portfolio.rebalancing

import com.nchechin.portfolio.rebalancing.domain.Customer
import com.nchechin.portfolio.rebalancing.domain.CustomerDetails
import com.nchechin.portfolio.rebalancing.domain.Trade
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TradeService(
    val strategyService: StrategyService
) {

    fun getTradeForCustomer(customer: Customer, customerDetails: CustomerDetails): Trade {
        val strategy = strategyService.getMatchingStrategyForCustomer(customer)
        val totalAssets = customerDetails.stocks + customerDetails.bonds + customerDetails.cash
        val newStocks = totalAssets * strategy.stocksPercentage.toBigDecimal() / BigDecimal.valueOf(100)
        val newBonds = totalAssets * strategy.bondsPercentage.toBigDecimal() / BigDecimal.valueOf(100)
        val newCash = totalAssets * strategy.cashPercentage.toBigDecimal() / BigDecimal.valueOf(100)
        return Trade(
            customerId = customer.customerId,
            stocks = newStocks - customerDetails.stocks,
            bonds = newBonds - customerDetails.bonds,
            cash = newCash - customerDetails.cash
        )
    }
}
package com.nchechin.portfolio.rebalancing

import com.nchechin.portfolio.rebalancing.domain.CustomerDetails
import com.nchechin.portfolio.rebalancing.domain.Trade
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "FinancialPortfolioService", url = "http://localhost:8081/financial_portfolio_service/")
interface FinancialPortfolioServiceFeignClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/customer/{id}"])
    fun getCustomerDetailsById(@PathVariable("id") id: Long): CustomerDetails

    @RequestMapping(method = [RequestMethod.POST], value = ["/execute"])
    fun executeTrades(trades: List<Trade>)

}
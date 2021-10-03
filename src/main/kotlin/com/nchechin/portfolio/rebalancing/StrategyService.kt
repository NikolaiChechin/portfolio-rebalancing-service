package com.nchechin.portfolio.rebalancing

import com.nchechin.portfolio.rebalancing.domain.Customer
import com.nchechin.portfolio.rebalancing.domain.Strategy
import com.nchechin.portfolio.rebalancing.utils.DEFAULT_STRATEGY
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Period

@Service
class StrategyService(
    val csvReaderService: CsvReaderService
) {

    fun getMatchingStrategyForCustomer(customer: Customer): Strategy {
        val strategies = csvReaderService.readStrategies()
        val matchingStrategy = strategies
            .filter { customer.riskLevel >= it.minRiskLevel && customer.riskLevel <= it.maxRiskLevel }
            .filter { strategy ->
                val dateOfBirth = LocalDate.parse(customer.dateOfBirth)
                val age = Period.between(dateOfBirth, LocalDate.now()).years
                val yearsToRetirement = customer.retirementAge - age
                yearsToRetirement >= strategy.minYearsToRetirement && yearsToRetirement <= strategy.maxYearsToRetirement
            }
            .getOrElse(0) { DEFAULT_STRATEGY }
        return matchingStrategy
    }

}
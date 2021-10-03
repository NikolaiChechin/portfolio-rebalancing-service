package com.nchechin.portfolio.rebalancing

import com.nchechin.portfolio.rebalancing.domain.Customer
import com.nchechin.portfolio.rebalancing.domain.CustomerDetails
import com.nchechin.portfolio.rebalancing.domain.Strategy
import java.math.BigDecimal

fun getTestCustomer1() =
    Customer(
        customerId = 1,
        email = "bob@bob.com",
        dateOfBirth = "1961-04-29",
        riskLevel = 3,
        retirementAge = 65
    )

fun getTestCustomer2() =
    Customer(
        customerId = 2,
        email = "sally@gmail.com",
        dateOfBirth = "1978-05-01",
        riskLevel = 8,
        retirementAge = 67
    )

fun getTestCustomers() =
    listOf(
        getTestCustomer1(),
        getTestCustomer2()
    )

fun getTestStrategies() =
    listOf(
        getTestStrategy1(),
        getTestStrategy2(),
        getTestStrategy3()
    )

fun getTestStrategy1() = Strategy(
    strategyId = 1,
    minRiskLevel = 0,
    maxRiskLevel = 3,
    minYearsToRetirement = 20,
    maxYearsToRetirement = 30,
    stocksPercentage = 20,
    cashPercentage = 20,
    bondsPercentage = 60
)

fun getTestStrategy2() = Strategy(
    strategyId = 2,
    minRiskLevel = 0,
    maxRiskLevel = 3,
    minYearsToRetirement = 10,
    maxYearsToRetirement = 20,
    stocksPercentage = 10,
    cashPercentage = 20,
    bondsPercentage = 70
)

fun getTestStrategy3() = Strategy(
    strategyId = 3,
    minRiskLevel = 6,
    maxRiskLevel = 9,
    minYearsToRetirement = 20,
    maxYearsToRetirement = 30,
    stocksPercentage = 10,
    cashPercentage = 0,
    bondsPercentage = 90
)

fun getTestCustomerDetails1() =
    CustomerDetails(
        customerId = 1,
        stocks = BigDecimal.valueOf(6700),
        bonds = BigDecimal.valueOf(1200),
        cash = BigDecimal.valueOf(400)
    )

fun getTestCustomerDetails2() =
    CustomerDetails(
        customerId = 2,
        stocks = BigDecimal.valueOf(5000),
        bonds = BigDecimal.valueOf(200),
        cash = BigDecimal.valueOf(1300)
    )
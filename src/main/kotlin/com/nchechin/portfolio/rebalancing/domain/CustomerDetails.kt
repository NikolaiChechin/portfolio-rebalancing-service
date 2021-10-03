package com.nchechin.portfolio.rebalancing.domain

import java.math.BigDecimal

data class CustomerDetails(
    val customerId: Long,
    val stocks: BigDecimal,
    val bonds: BigDecimal,
    val cash: BigDecimal
)
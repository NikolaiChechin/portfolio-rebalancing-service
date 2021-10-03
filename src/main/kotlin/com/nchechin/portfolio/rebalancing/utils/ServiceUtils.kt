package com.nchechin.portfolio.rebalancing.utils

import com.nchechin.portfolio.rebalancing.domain.Strategy

val DEFAULT_STRATEGY = Strategy(
    strategyId = 0,
    minRiskLevel = 0,
    maxRiskLevel = 99,
    minYearsToRetirement = 0,
    maxYearsToRetirement = 99,
    stocksPercentage = 0,
    cashPercentage = 100,
    bondsPercentage = 0
)
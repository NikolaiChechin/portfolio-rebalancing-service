package com.nchechin.portfolio.rebalancing.domain

import com.nchechin.portfolio.rebalancing.utils.NoArgConstructor
import com.opencsv.bean.CsvBindByName

@NoArgConstructor
data class Strategy(
    @CsvBindByName
    val strategyId: Long,
    @CsvBindByName
    val minRiskLevel: Int,
    @CsvBindByName
    val maxRiskLevel: Int,
    @CsvBindByName
    val minYearsToRetirement: Int,
    @CsvBindByName
    val maxYearsToRetirement: Int,
    @CsvBindByName
    val stocksPercentage: Int,
    @CsvBindByName
    val cashPercentage: Int,
    @CsvBindByName
    val bondsPercentage: Int
)
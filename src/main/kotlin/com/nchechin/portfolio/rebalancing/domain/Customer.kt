package com.nchechin.portfolio.rebalancing.domain

import com.nchechin.portfolio.rebalancing.utils.NoArgConstructor
import com.opencsv.bean.CsvBindByName

@NoArgConstructor
data class Customer(
    @CsvBindByName
    val customerId: Long,
    @CsvBindByName
    val email: String,
    @CsvBindByName
    val dateOfBirth: String,
    @CsvBindByName
    val riskLevel: Int,
    @CsvBindByName
    val retirementAge: Int
)
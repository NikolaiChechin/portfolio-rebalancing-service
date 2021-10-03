package com.nchechin.portfolio.rebalancing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class StrategyServiceTest {

    private val csvReaderService: CsvReaderService = mock()

    private val strategyService = StrategyService(csvReaderService)

    @Test
    fun `default strategy found correctly`() {
        //given
        val testCustomer = getTestCustomer1()
        whenever(csvReaderService.readStrategies()).thenReturn(getTestStrategies())

        //when
        val matchingStrategy = strategyService.getMatchingStrategyForCustomer(testCustomer)

        //then
        assertEquals(0, matchingStrategy.strategyId)
    }

    @Test
    fun `matching strategy found correctly`() {
        //given
        val testCustomer = getTestCustomer2()
        whenever(csvReaderService.readStrategies()).thenReturn(getTestStrategies())

        //when
        val matchingStrategy = strategyService.getMatchingStrategyForCustomer(testCustomer)

        //then
        assertEquals(3, matchingStrategy.strategyId)
    }
}
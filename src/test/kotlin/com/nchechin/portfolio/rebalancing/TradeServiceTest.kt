package com.nchechin.portfolio.rebalancing

import com.nchechin.portfolio.rebalancing.domain.Trade
import com.nchechin.portfolio.rebalancing.utils.DEFAULT_STRATEGY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal

class TradeServiceTest {

    private val strategyService: StrategyService = mock()

    private val tradeService = TradeService(
        strategyService
    )

    @Test
    fun `trade is calculated correctly for customer 1`() {
        //given
        val customer = getTestCustomer1()
        whenever(strategyService.getMatchingStrategyForCustomer(customer))
            .thenReturn(DEFAULT_STRATEGY)
        val expectedTrade = Trade(
            customerId = 1,
            stocks = BigDecimal.valueOf(-6700),
            bonds = BigDecimal.valueOf(-1200),
            cash = BigDecimal.valueOf(7900)
        )

        //when
        val trade = tradeService.getTradeForCustomer(customer, getTestCustomerDetails1())

        //then
        assertEquals(expectedTrade, trade)
    }

    @Test
    fun `trade is calculated correctly for customer 2`() {
        //given
        val customer = getTestCustomer2()
        whenever(strategyService.getMatchingStrategyForCustomer(customer))
            .thenReturn(getTestStrategy3())
        val expectedTrade = Trade(
            customerId = 2,
            stocks = BigDecimal.valueOf(-4350),
            bonds = BigDecimal.valueOf(5650),
            cash = BigDecimal.valueOf(-1300)
        )

        //when
        val trade = tradeService.getTradeForCustomer(customer, getTestCustomerDetails2())

        //then
        assertEquals(expectedTrade, trade)
    }


}
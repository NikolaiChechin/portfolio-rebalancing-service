package com.nchechin.portfolio.rebalancing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test


class CsvReaderServiceTest {

    private val csvReaderService = CsvReaderService()

    @Test
    fun `customers parsed correctly`() {
        //given
        val expectedCustomers = getTestCustomers()

        //when
        val customers = csvReaderService.readCustomers()

        //then
        assertNotNull(customers)
        assertEquals(expectedCustomers, customers)
    }

    @Test
    fun `strategies parsed correctly`() {
        //given
        val expectedStrategies = getTestStrategies()

        //when
        val strategies = csvReaderService.readStrategies()

        //then
        assertNotNull(strategies)
        assertEquals(expectedStrategies, strategies)
    }

}
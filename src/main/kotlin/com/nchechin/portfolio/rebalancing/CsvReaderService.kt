package com.nchechin.portfolio.rebalancing

import com.nchechin.portfolio.rebalancing.domain.Customer
import com.nchechin.portfolio.rebalancing.domain.Strategy
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.exceptions.CsvException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths


@Service
class CsvReaderService {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun readCustomers() =
        parseDataFromCsv<Customer>("customers.csv")

    fun readStrategies() =
        parseDataFromCsv<Strategy>("strategy.csv")

    private inline fun <reified T> parseDataFromCsv(filePath: String): List<T> {
        val systemResource = ClassLoader.getSystemResource(filePath)
        if (systemResource == null) {
            logger.error("No resource found by path $filePath")
            emptyList<T>()
        }
        val path = Paths.get(systemResource.toURI())
        val entities = try {
            Files.newBufferedReader(path).use {
                CsvToBeanBuilder<T>(it)
                    .withType(T::class.java)
                    .build()
                    .parse()
            }
        } catch (ex: CsvException) {
            logger.error("An exception occurred during parsing resource $filePath", ex)
            emptyList<T>()
        }
        return entities
    }

}
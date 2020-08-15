package com.tblxchallenge.fleetoperator.services

import com.ninjasquad.springmockk.MockkBean
import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.repositories.OperatorRepository
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureDataMongo
class OperatorServiceTest {

    @Autowired
    private lateinit var operatorService: OperatorService

    @MockkBean
    private lateinit var operatorRepository: OperatorRepository

    private val startDate = LocalDate.of(2012, 11, 5)
    private val endDate = LocalDate.of(2012, 11, 11)
    private val trace1 = Trace("1", 1123144123, "", 0, "",
            startDate, 2314123, "PO", false, 3.5,
            6.7, 0, 0, 2, "0", 0)
    private val trace2 = Trace("1", 1123144123, "", 0, "",
            startDate, 2314123, "D2", false, 3.5,
            6.7, 0, 0, 2, "0", 0)

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        every {
            operatorRepository.findAllByTimeFrameBetween(startDate, endDate)
        } returns mockTraceList()
    }

    @Test
    fun `Given a start date and end date then return list of operators for date interval`() {
        val result = operatorService.findRunningOperators(startDate, endDate)

        assertThat(result)
                .isNotNull
                .isNotEmpty
                .hasOnlyElementsOfType(String::class.java)
                .hasSize(2)
                .contains(trace1.operator)
                .contains(trace2.operator)
    }

    fun mockTraceList() = listOf(trace1, trace2)
}
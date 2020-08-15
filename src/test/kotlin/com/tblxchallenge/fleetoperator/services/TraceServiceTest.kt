package com.tblxchallenge.fleetoperator.services

import com.ninjasquad.springmockk.MockkBean
import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.repositories.TraceRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
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
class TraceServiceTest {
    @Autowired
    private lateinit var traceService: TraceService

    @MockkBean
    private lateinit var traceRepository: TraceRepository

    private val vehicleId = 33228
    private val startDate = LocalDate.of(2012, 11, 5)
    private val endDate = LocalDate.of(2012, 11, 11)
    private val trace1 = Trace("1", 1123144123, "", 0, "",
            startDate, 2314123, "PO", false, 3.5,
            6.7, 0, 0, 33228, "0", 0)
    private val trace2 = Trace("1", 1123144123, "", 0, "",
            startDate, 2314123, "PO", false, 3.5,
            6.7, 0, 0, 33228, "0", 0)

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        every {
            traceRepository.findTraceForVehicle(startDate, endDate, vehicleId)
        } returns mockTraceList()
    }

    @Test
    fun `Given a start date, end date and vehicleId return Trace list for that vehicle`() {
        val result = traceService.findTraceForVehicle(startDate, endDate, vehicleId)
        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isNotEmpty
        Assertions.assertThat(result).hasOnlyElementsOfType(Trace::class.java)
        Assertions.assertThat(result).hasSize(2)
        Assertions.assertThat(result).contains(trace1)
        Assertions.assertThat(result).contains(trace2)
    }

    fun mockTraceList() = listOf(trace1, trace2)
}
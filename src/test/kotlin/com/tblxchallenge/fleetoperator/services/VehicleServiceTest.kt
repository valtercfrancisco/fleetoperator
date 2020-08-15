package com.tblxchallenge.fleetoperator.services

import com.ninjasquad.springmockk.MockkBean
import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.repositories.VehicleRepository
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
class VehicleServiceTest {

    @Autowired
    private lateinit var vehicleService: VehicleService

    @MockkBean
    private lateinit var vehicleRepository: VehicleRepository

    private val operator = "PO"
    private val atStop = 1
    private val startDate = LocalDate.of(2012, 11, 5)
    private val endDate = LocalDate.of(2012, 11, 11)
    private val trace1 = Trace("1", 1123144123, "", 0, "",
            startDate, 2314123, "PO", false, 3.5,
            6.7, 0, 0, 33228, "0", 0)
    private val trace2 = Trace("1", 1123144123, "", 0, "",
            startDate, 2314123, "PO", false, 3.5,
            6.7, 0, 0, 40002, "0", 0)

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        every {
            vehicleRepository.findVehiclesForOperator(startDate, endDate, operator)
        } returns mockTraceList()

        every {
            vehicleRepository.findVehiclesForOperatorAtStop(startDate, endDate, operator, atStop)
        } returns mockTraceList()
    }

    @Test
    fun `Given a start date, end date and operator return vehicle id list for that operator`() {
        val result = vehicleService.findVehiclesForOperator(startDate, endDate, operator)
        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isNotEmpty
        Assertions.assertThat(result).hasOnlyElementsOfType(Integer::class.java)
        Assertions.assertThat(result).hasSize(2)
        Assertions.assertThat(result).contains(trace1.vehicleId)
        Assertions.assertThat(result).contains(trace2.vehicleId)
    }

    @Test
    fun `Given a start date, end date and operator return vehicles at stop list for that operator`() {
        val result = vehicleService.findVehiclesAtStop(startDate, endDate, operator)
        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isNotEmpty
        Assertions.assertThat(result).hasOnlyElementsOfType(Integer::class.java)
        Assertions.assertThat(result).hasSize(2)
        Assertions.assertThat(result).contains(trace1.vehicleId)
        Assertions.assertThat(result).contains(trace2.vehicleId)
    }

    fun mockTraceList() = listOf(trace1, trace2)
}
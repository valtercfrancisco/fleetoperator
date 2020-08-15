package com.tblxchallenge.fleetoperator.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.services.TraceService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TraceControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var traceService: TraceService

    private val urlBase: String = "/trace"

    private val vehicleId = 33228
    private val startDate = LocalDate.of(2012, 11, 5)
    private val endDate = LocalDate.of(2012, 11, 11)

    private val trace1 = Trace("1", 1123144123, "123", 0, "21ER",
            startDate, 2314123, "PO", false, 3.5,
            6.7, 0, 0, 33228, "0", 0)

    @Test
    @Throws(Exception::class)
    fun `Given a start date, end date and a vehicleId return trace list for date interval`() {
        val response = mockTraceList()
        every {
            traceService.findTraceForVehicle(startDate, endDate, vehicleId)
        } returns response

        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("vehicleId", vehicleId.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(response.size))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(response[0].id!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timestamp").value(response[0].timestamp!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lineId").value(response[0].lineId!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].direction").value(response[0].direction!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].journeyPatternId").value(response[0].journeyPatternId!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timeFrame").value(response[0].timeFrame!!.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].operator").value(response[0].operator!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].congestion").value(response[0].congestion!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].longitude").value(response[0].longitude!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].latitude").value(response[0].latitude!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].delay").value(response[0].delay!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].blockId").value(response[0].blockId!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicleId").value(response[0].vehicleId!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stopId").value(response[0].stopId!!))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].atStop").value(response[0].atStop!!))

    }

    @Test
    fun `Given invalid vehicleId return bad request with custom message`() {
        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("vehicleId", " ")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid Vehicle ID."))
    }

    @Test
    fun `Given a start date that is after the end date return bad request with custom message`() {
        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", endDate.toString())
                .param("endDate", startDate.toString())
                .param("vehicleId", vehicleId.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("End time must be after start time."))
    }

    @Test
    fun `Missing parameter return bad request`() {
        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", startDate.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    fun mockTraceList() = listOf(trace1)
}
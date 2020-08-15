package com.tblxchallenge.fleetoperator.controllers

import com.ninjasquad.springmockk.MockkBean
import com.tblxchallenge.fleetoperator.services.VehicleService
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
class VehicleControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var vehicleService: VehicleService

    private val urlBase: String = "/vehicles"
    private val urlAtStop: String = "$urlBase/atStop"

    private val operator = "PO"
    private val startDate = LocalDate.of(2012, 11, 5)
    private val endDate = LocalDate.of(2012, 11, 11)

    @Test
    @Throws(Exception::class)
    fun `Given a start date, end date and an operator return vehicle list for date interval`() {
        val response = mockVehicleIdList()
        every {
            vehicleService.findVehiclesForOperator(startDate, endDate, operator)
        } returns response

        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("operator", operator)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(response.size))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(response[0]))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(response[1]))

    }

    @Test
    @Throws(Exception::class)
    fun `Given a start date, end date and an operator return list of vehicles at stop for date interval`() {
        val response = mockVehicleIdList()
        every {
            vehicleService.findVehiclesAtStop(startDate, endDate, operator)
        } returns response

        mvc.perform(MockMvcRequestBuilders.get(urlAtStop)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("operator", operator)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(response.size))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(response[0]))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(response[1]))
    }

    @Test
    fun `Given invalid operator return bad request with custom message`() {
        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("operator", " ")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid Operator ID."))
    }

    @Test
    fun `Given a start date that is after the end date return bad request with custom message`() {
        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", endDate.toString())
                .param("endDate", startDate.toString())
                .param("operator", operator)
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

    fun mockVehicleIdList() = listOf(40002, 33588)
}
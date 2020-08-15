package com.tblxchallenge.fleetoperator.controllers

import com.ninjasquad.springmockk.MockkBean
import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.services.OperatorService
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.lang.IllegalArgumentException
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OperatorControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var operatorService: OperatorService

    private val urlBase: String = "/operators"

    private val startDate = LocalDate.of(2012, 11, 5)
    private val endDate = LocalDate.of(2012, 11, 11)

    @Test
    @Throws(Exception::class)
    fun `Given a start date and end date return operator list for date interval`() {
        val response = mockOperatorList()
        every {
            operatorService.findRunningOperators(startDate, endDate)
        } returns response

        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(response.size))
                .andExpect(jsonPath("$[0]").value(response[0]))
                .andExpect(jsonPath("$[1]").value(response[1]))

    }

    @Test
    fun `Given a start date that is after the end date return bad request with custom message`() {
        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", endDate.toString())
                .param("endDate", startDate.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("End time must be after start time."))
    }

    @Test
    fun `Given only one date return bad request`() {
        mvc.perform(MockMvcRequestBuilders.get(urlBase)
                .param("startDate", startDate.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
    }

    fun mockOperatorList() = listOf( "PO", "SL")
}
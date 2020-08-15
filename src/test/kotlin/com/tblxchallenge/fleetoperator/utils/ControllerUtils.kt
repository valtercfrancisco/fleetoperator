package com.tblxchallenge.fleetoperator.utils

import com.tblxchallenge.fleetoperator.documents.Trace
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate.now

class ControllerUtils {

    private val trace1 = Trace("1", 1123144123, "123", 0,
            "21ER", now(), 2314123, "PO",
            false, 3.5, 6.7, 0, 0,
            33228, "0", 0)
    private val trace2 = Trace("1", 1123144123, "123", 0,
            "21ER", now(), 2314123, "D2",
            false, 3.5, 6.7, 0, 0,
            40002, "0", 0)

    private val traceList = listOf(trace1, trace2)

    @Test
    fun `Given trace list return string list of operator field`() {
        val result = traceList.toOperatorList()

        assertThat(result)
                .hasOnlyElementsOfType(String::class.java)
                .hasSize(traceList.size)
                .contains(trace1.operator)
                .contains(trace2.operator)
    }

    @Test
    fun `Given trace list return int list of vehicle id field`() {
        val result = traceList.toVehicleList()

        assertThat(result)
                .hasOnlyElementsOfType(Integer::class.java)
                .hasSize(traceList.size)
                .contains(trace1.vehicleId)
                .contains(trace2.vehicleId)
    }
}
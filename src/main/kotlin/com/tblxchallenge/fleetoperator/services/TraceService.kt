package com.tblxchallenge.fleetoperator.services

import com.tblxchallenge.fleetoperator.documents.Trace
import java.time.LocalDate

interface TraceService {
    fun findTraceForVehicle(startTime: LocalDate, endTime: LocalDate, vehicleId: String) : List<Trace>
}
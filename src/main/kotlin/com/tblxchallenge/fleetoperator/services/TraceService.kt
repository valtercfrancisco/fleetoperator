package com.tblxchallenge.fleetoperator.services

import com.tblxchallenge.fleetoperator.documents.Trace
import java.time.LocalDate

interface TraceService {
    fun findTraceForVehicle(startDate: LocalDate, endDate: LocalDate, vehicleId: String) : List<Trace>
}
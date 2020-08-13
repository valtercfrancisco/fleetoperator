package com.tblxchallenge.fleetoperator.services

import com.tblxchallenge.fleetoperator.documents.Trace
import java.time.LocalDate

interface VehicleService {
    fun findVehiclesForOperator(startTime: LocalDate, endTime: LocalDate, operator: String) : List<Trace>
    fun findVehiclesAtStop(startTime: LocalDate, endTime: LocalDate, operator: String) : List<Trace>
}
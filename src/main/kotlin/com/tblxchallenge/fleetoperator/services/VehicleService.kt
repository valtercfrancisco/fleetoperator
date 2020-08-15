package com.tblxchallenge.fleetoperator.services

import java.time.LocalDate

interface VehicleService {
    fun findVehiclesForOperator(startTime: LocalDate, endTime: LocalDate, operator: String) : List<Int>
    fun findVehiclesAtStop(startTime: LocalDate, endTime: LocalDate, operator: String) : List<Int>
}
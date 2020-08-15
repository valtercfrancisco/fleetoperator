package com.tblxchallenge.fleetoperator.controllers

import com.tblxchallenge.fleetoperator.services.VehicleService
import com.tblxchallenge.fleetoperator.utils.validateDates
import com.tblxchallenge.fleetoperator.utils.validateOperatorId
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/vehicles")
class VehicleController(val vehicleService: VehicleService) {

    @GetMapping
    fun listVehiclesForOperators(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate,
            @RequestParam operator: String
    ): ResponseEntity<List<Int>> {
        validateDates(startDate, endDate)
        operator.validateOperatorId()
        val result = vehicleService.findVehiclesForOperator(startDate, endDate, operator)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/atStop")
    fun listVehiclesAtStop(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate,
            @RequestParam operator: String
    ): ResponseEntity<List<Int>> {
        validateDates(startDate, endDate)
        operator.validateOperatorId()
        val result = vehicleService.findVehiclesAtStop(startDate, endDate, operator)
        return ResponseEntity.ok(result)
    }
}
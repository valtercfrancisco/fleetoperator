package com.tblxchallenge.fleetoperator.controller

import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.services.VehicleService
import com.tblxchallenge.fleetoperator.utils.validateDates
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
    ): ResponseEntity<List<String>> {
        validateDates(startDate, endDate)
        val result = vehicleService.findVehiclesForOperator(startDate, endDate, operator).toVehicleList()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/atStop")
    fun listVehiclesAtStop(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate,
            @RequestParam operator: String
    ): ResponseEntity<List<String>> {
        validateDates(startDate, endDate)
        val result = vehicleService.findVehiclesAtStop(startDate, endDate, operator).toVehicleList()
        return ResponseEntity.ok(result)
    }

    private fun List<Trace>.toVehicleList(): List<String> =
            mapNotNull { it.vehicleId.toString() }.distinct()

}
package com.tblxchallenge.fleetoperator.controllers

import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.services.TraceService
import com.tblxchallenge.fleetoperator.utils.validateDates
import com.tblxchallenge.fleetoperator.utils.validateVehicleId
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/trace")
class TraceController(val traceService: TraceService) {

    @GetMapping
    fun listOperators(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate,
            @RequestParam vehicleId: String
    ) : ResponseEntity<List<Trace>> {
        validateDates(startDate, endDate)
        vehicleId.validateVehicleId()
        val result = traceService.findTraceForVehicle(startDate, endDate, vehicleId.toInt())
        return ResponseEntity.ok(result)
    }
}
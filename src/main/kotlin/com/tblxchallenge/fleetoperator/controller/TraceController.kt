package com.tblxchallenge.fleetoperator.controller

import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.services.TraceService
import com.tblxchallenge.fleetoperator.utils.validateDates
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
            @RequestParam vehicleId: Int
    ) : ResponseEntity<List<Trace>> {
        validateDates(startDate, endDate)
        val result = traceService.findTraceForVehicle(startDate, endDate, vehicleId)
        return ResponseEntity.ok(result)
    }
}
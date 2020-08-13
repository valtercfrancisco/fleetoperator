package com.tblxchallenge.fleetoperator.controller

import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.services.OperatorService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException
import java.time.LocalDate

@RestController
@RequestMapping("/operators")
class OperatorController(val operatorService: OperatorService) {

    @GetMapping
    fun listOperators(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startTime: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endTime: LocalDate
    ) : ResponseEntity<List<String>> {
        validateParams(startTime, endTime)
        val result = operatorService.findRunningOperators(startTime, endTime).toOperatorList()
        return ResponseEntity.ok(result)
    }

    private fun validateParams(startTime: LocalDate, endTime: LocalDate) {
        if(startTime.isAfter(endTime)) {
            throw IllegalArgumentException("End time must be after start time.")
        }
    }

    private fun List<Trace>.toOperatorList(): List<String> =
            mapNotNull { it.operator }.distinct()
}

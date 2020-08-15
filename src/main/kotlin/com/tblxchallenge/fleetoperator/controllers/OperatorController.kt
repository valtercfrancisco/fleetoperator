package com.tblxchallenge.fleetoperator.controllers

import com.tblxchallenge.fleetoperator.services.OperatorService
import com.tblxchallenge.fleetoperator.utils.toOperatorList
import com.tblxchallenge.fleetoperator.utils.validateDates
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/operators")
class OperatorController(val operatorService: OperatorService) {

    @GetMapping
    fun listOperators(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate
    ) : ResponseEntity<List<String>> {
        validateDates(startDate, endDate)
        val result = operatorService.findRunningOperators(startDate, endDate)
        return ResponseEntity.ok(result)
    }
}

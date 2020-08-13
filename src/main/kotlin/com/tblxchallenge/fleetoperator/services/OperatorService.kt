package com.tblxchallenge.fleetoperator.services

import com.tblxchallenge.fleetoperator.documents.Trace
import java.time.LocalDate

interface OperatorService {
    fun findRunningOperators(startTime: LocalDate, endTime: LocalDate): List<Trace>
}
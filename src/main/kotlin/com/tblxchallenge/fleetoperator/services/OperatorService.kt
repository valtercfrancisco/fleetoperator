package com.tblxchallenge.fleetoperator.services

import com.tblxchallenge.fleetoperator.documents.Trace
import java.time.LocalDate

interface OperatorService {
    fun findRunningOperators(startDate: LocalDate, endDate: LocalDate): List<Trace>
}
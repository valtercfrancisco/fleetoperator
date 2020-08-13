package com.tblxchallenge.fleetoperator.services.impl

import com.tblxchallenge.fleetoperator.documents.Trace
import com.tblxchallenge.fleetoperator.repository.OperatorRepository
import com.tblxchallenge.fleetoperator.services.OperatorService
import org.springframework.stereotype.Service
import java.sql.Date
import java.time.LocalDate

@Service
class OperatorServiceImpl(private val operatorRepository: OperatorRepository) : OperatorService {
    override fun findRunningOperators(startTime: LocalDate, endTime: LocalDate) =
            operatorRepository.findAllByTimeFrameBetween(startTime, endTime)
}
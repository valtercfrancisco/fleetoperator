package com.tblxchallenge.fleetoperator.services.impl

import com.tblxchallenge.fleetoperator.repositories.OperatorRepository
import com.tblxchallenge.fleetoperator.services.OperatorService
import com.tblxchallenge.fleetoperator.utils.toOperatorList
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OperatorServiceImpl(private val operatorRepository: OperatorRepository) : OperatorService {
    override fun findRunningOperators(startDate: LocalDate, endDate: LocalDate) =
            operatorRepository.findAllByTimeFrameBetween(startDate, endDate).toOperatorList()
}
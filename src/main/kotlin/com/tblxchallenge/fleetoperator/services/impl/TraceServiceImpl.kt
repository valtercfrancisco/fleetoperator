package com.tblxchallenge.fleetoperator.services.impl

import com.tblxchallenge.fleetoperator.repositories.TraceRepository
import com.tblxchallenge.fleetoperator.services.TraceService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TraceServiceImpl(private val traceRepository: TraceRepository) : TraceService {
    override fun findTraceForVehicle(startDate: LocalDate, endDate: LocalDate, vehicleId: Int) =
            traceRepository.findTraceForVehicle(startDate, endDate, vehicleId)
}
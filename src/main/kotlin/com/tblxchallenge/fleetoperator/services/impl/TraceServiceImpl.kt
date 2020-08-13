package com.tblxchallenge.fleetoperator.services.impl

import com.tblxchallenge.fleetoperator.repository.TraceRepository
import com.tblxchallenge.fleetoperator.services.TraceService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TraceServiceImpl(private val traceRepository: TraceRepository) : TraceService {
    override fun findTraceForVehicle(startTime: LocalDate, endTime: LocalDate, vehicleId: String) =
            traceRepository.findTraceForVehicle(startTime, endTime, vehicleId)
}
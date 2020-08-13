package com.tblxchallenge.fleetoperator.services.impl

import com.tblxchallenge.fleetoperator.repository.VehicleRepository
import com.tblxchallenge.fleetoperator.services.VehicleService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class VehicleServiceImpl(private val vehicleRepository: VehicleRepository) : VehicleService {
    override fun findVehiclesForOperator(startTime: LocalDate, endTime: LocalDate, operator: String) =
            vehicleRepository.findVehiclesForOperator(startTime, endTime, operator)

    override fun findVehiclesForOperatorAtStop(startTime: LocalDate, endTime: LocalDate, operator: String) =
            vehicleRepository.findVehiclesForOperatorAtStop(startTime, endTime, operator, 1)
}
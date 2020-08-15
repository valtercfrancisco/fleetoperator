package com.tblxchallenge.fleetoperator.repositories

import com.tblxchallenge.fleetoperator.documents.Trace
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDate

interface VehicleRepository: MongoRepository<Trace, String> {
    @Query(value = "{'timeFrame' : {\$gte : ?0, \$lte : ?1}, 'operator' : ?2}", fields="{ '_id' : 0, 'vehicleId' : 1}")
    fun findVehiclesForOperator(startDate: LocalDate, endDate: LocalDate,
                                operator: String): List<Trace>

    @Query(value = "{'timeFrame' : {\$gte : ?0, \$lte : ?1}, 'operator' : ?2, 'atStop' : ?3}", fields="{ '_id' : 0, 'vehicleId' : 1}")
    fun findVehiclesForOperatorAtStop(startDate: LocalDate, endDate: LocalDate,
                                      operator: String, atStop: Int = 1): List<Trace>
}
package com.tblxchallenge.fleetoperator.repository

import com.tblxchallenge.fleetoperator.documents.Trace
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDate

interface VehicleRepository: MongoRepository<Trace, String> {
    @Query(value = "{'timeFrame' : {\$gt : ?0, \$lt : ?1}, 'operator' : ?2}",
            fields = "{_id : 0, operator : 1}")
    fun findVehiclesForOperator(startTime: LocalDate, endTime: LocalDate,
                                operator: String): List<Trace>

    @Query(value = "{'timeFrame' : {\$gt : ?0, \$lt : ?1}, 'operator' : ?2, 'atStop' : ?4}",
            fields = "{_id : 0, operator : 1}")
    fun findVehiclesForOperatorAtStop(startTime: LocalDate, endTime: LocalDate,
                                      operator: String, atStop: Int): List<Trace>
}
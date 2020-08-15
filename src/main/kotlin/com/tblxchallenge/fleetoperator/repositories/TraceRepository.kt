package com.tblxchallenge.fleetoperator.repositories

import com.tblxchallenge.fleetoperator.documents.Trace
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDate

interface TraceRepository : MongoRepository<Trace, Int> {
    @Query(value = "{'timeFrame' : {\$gte : ?0, \$lte : ?1}, 'vehicleId' : ?2}")
    fun findTraceForVehicle(startTime: LocalDate, endTime: LocalDate, vehicleId: Int): List<Trace>
}
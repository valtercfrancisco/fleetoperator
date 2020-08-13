package com.tblxchallenge.fleetoperator.repository

import com.tblxchallenge.fleetoperator.documents.Trace
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDate

interface TraceRepository : MongoRepository<Trace, String> {
    @Query(value = "{'timeFrame' : {\$gt : ?0, \$lt : ?1}, 'vehicleId' : ?2}")
    fun findTraceForVehicle(startTime: LocalDate, endTime: LocalDate, vehicleId: String): List<Trace>
}
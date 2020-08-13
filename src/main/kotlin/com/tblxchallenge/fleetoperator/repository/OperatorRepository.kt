package com.tblxchallenge.fleetoperator.repository

import com.tblxchallenge.fleetoperator.documents.Trace
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDate

interface OperatorRepository: MongoRepository<Trace, String> {
    @Query(value = "{'timeFrame' : {\$gte : ?0, \$lte : ?1}}", fields="{ '_id' : 0, 'operator' : 1}")
    fun findAllByTimeFrameBetween(startDate: LocalDate, endDate: LocalDate): List<Trace>

}
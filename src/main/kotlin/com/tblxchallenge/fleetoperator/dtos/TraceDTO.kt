package com.tblxchallenge.fleetoperator.dtos

import java.util.*

data class TraceDTO(
        var id: String? = null,
        val timestamp: String,
        val lineId: Long,
        val direction: Long,
        val journeyPatternId: String,
        val timeFrame: Date?,
        val vehicleJourneyId: Long,
        val operator: String,
        val congestion: Boolean,
        val lon: Double,
        val lat: Double,
        val delay: Long,
        val blockId: Long,
        val vehicleId: Long,
        val stopId: Long,
        val atStop: Int
)
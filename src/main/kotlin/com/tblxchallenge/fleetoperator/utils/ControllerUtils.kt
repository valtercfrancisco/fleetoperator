package com.tblxchallenge.fleetoperator.utils

import com.tblxchallenge.fleetoperator.documents.Trace

fun List<Trace>.toOperatorList(): List<String> =
        mapNotNull { it.operator }.distinct()

fun List<Trace>.toVehicleList(): List<String> =
        mapNotNull { it.vehicleId.toString() }.distinct()
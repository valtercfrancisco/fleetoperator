package com.tblxchallenge.fleetoperator.utils

import java.lang.IllegalArgumentException

fun String.validateOperatorId() {
    if(isNullOrBlank()) {
        throw IllegalArgumentException("Invalid Operator ID.")
    }
}

fun String.validateVehicleId() {when {
        isNullOrBlank() -> throw IllegalArgumentException("Invalid Vehicle ID.")
        toIntOrNull() == null ->
            throw IllegalArgumentException("Invalid Vehicle ID. Vehicle ID must be numeric.")
    }
}
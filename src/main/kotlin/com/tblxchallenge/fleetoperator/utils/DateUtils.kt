package com.tblxchallenge.fleetoperator.utils

import java.lang.IllegalArgumentException
import java.time.LocalDate

fun validateDates(startDate: LocalDate, endDate: LocalDate) {
    if(startDate.isAfter(endDate)) {
        throw IllegalArgumentException("End time must be after start time.")
    }
}
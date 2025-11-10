package com.nalepa.ticketing.ticketing.domain.docs

import io.micrometer.observation.Observation

data class ReportingContext(
    val nameXD: String,
    val number: String,
) : Observation.Context()
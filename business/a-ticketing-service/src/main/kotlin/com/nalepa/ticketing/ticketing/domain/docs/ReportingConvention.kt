package com.nalepa.ticketing.ticketing.domain.docs

import io.micrometer.common.KeyValues
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationConvention

class ReportingConvention : ObservationConvention<ReportingContext> {

    override fun getName(): String {
        return "dummy.ticket"
    }

    override fun supportsContext(p0: Observation.Context): Boolean {
        return p0 is ReportingContext
    }

    override fun getHighCardinalityKeyValues(context: ReportingContext): KeyValues {
        return KeyValues.of(
            ReportingDocumentation.HighCardinalityKeyNames.DUMMY_NUMBER.withValue(context.number),
        )
    }

    override fun getLowCardinalityKeyValues(context: ReportingContext): KeyValues {
        return KeyValues.of(
            ReportingDocumentation.LowCardinalityKeyNames.DUMMY_NAME.withValue(context.nameXD),
        )
    }
}

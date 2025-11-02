package com.nalepa.ticketing.reporting.domain

import io.micrometer.common.KeyValues
import io.micrometer.common.docs.KeyName
import io.micrometer.common.lang.NonNull
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationConvention
import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.docs.ObservationDocumentation
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class DummyReportUseCase(
    private val observationRegistry: ObservationRegistry,
) {

    private val convention = ReportingConvention()

    fun report(name: String, number: String): String {
        return ReportingDocumentation
            .REPORT
            .observation(
                convention,
                convention,
                { ReportingContext(name, number) },
                observationRegistry,
            )
            .observe(Supplier { internalReport(name, number) })!!
    }

    fun internalReport(name: String, number: String): String {
        Thread.sleep(number.toLong())
        return name
    }

}

enum class ReportingDocumentation : ObservationDocumentation {

    REPORT {
        override fun getDefaultConvention(): Class<out ObservationConvention<out Observation.Context>> {
            return ReportingConvention::class.java
        }

        override fun getHighCardinalityKeyNames(): Array<out KeyName?> {
            return HighCardinalityKeyNames.entries.toTypedArray()
        }

        override fun getLowCardinalityKeyNames(): Array<out KeyName?> {
            return LowCardinalityKeyNames.entries.toTypedArray()
        }


    };

    enum class HighCardinalityKeyNames : KeyName {
        DUMMY_NUMBER {
            @NonNull
            override fun asString(): String {
                return "dummy.number"
            }
        },
    }


    enum class LowCardinalityKeyNames : KeyName {
        DUMMY_NAME {
            @NonNull
            override fun asString(): String {
                return "dummy.name"
            }
        },
    }

}

class ReportingConvention : ObservationConvention<ReportingContext> {

    override fun getName(): String {
        return "dummy.report"
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

data class ReportingContext(
    val nameXD: String,
    val number: String,
) : Observation.Context()
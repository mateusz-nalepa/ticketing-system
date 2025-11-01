package com.nalepa.ticketing.reporting.api

import io.micrometer.common.KeyValues
import io.micrometer.common.docs.KeyName
import io.micrometer.common.lang.NonNull
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationConvention
import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.docs.ObservationDocumentation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.autoconfigure.tracing.zipkin.ZipkinProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.function.Supplier

@RestController
class ReportingEndpoint(
    private val registry: ObservationRegistry,
    private val zipkinProps: ZipkinProperties,
) {

    private val convetion = ReportingConvention()

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)


    @GetMapping("/dummy/{name}/{number}")
    fun dummyReporting(@PathVariable name: String, @PathVariable number: String): String {
        logger.info("REPORTING: name: $name, number: $number")
//        logger.info(zipkinProps.toString())

        return ReportingDocumentation
            .REPORT
            .observation(
                convetion,
                convetion,
                { ReportingContext(name, number) },
                registry,
            )
            .observe(Supplier { name })!!
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
package com.nalepa.ticketing.order.domain

import io.micrometer.common.KeyValues
import io.micrometer.common.docs.KeyName
import io.micrometer.common.lang.NonNull
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationConvention
import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.docs.ObservationDocumentation
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.client.RestClient
import java.util.function.Supplier

@Service
class DummyOrderUseCase(
    private val reportingServiceRestClient: RestClient,
    private val observationRegistry: ObservationRegistry,
) {

    private val convention = ReportingConvention()

    fun order(name: String, number: String): String {
        return ReportingDocumentation
            .REPORT
            .observation(
                convention,
                convention,
                { ReportingContext(name, number) },
                observationRegistry,
            )
            .observe(Supplier { internalOrder(name, number) })!!
    }
//    traceId -> 690a53282cec6f0ac95b4ad4dc532c8b
//    spanId -> 0c2db80f85370b84
//    traceparent: 00-690a53282cec6f0ac95b4ad4dc532c8b-b8dffc203607af65-01\r\n

    // interceptor nie dodaje headera traceParent, tylko poprzez ClientRequestObservationContext się to dzieje
    // a pod spodem jest:
//    io.micrometer.tracing.brave.bridge.W3CPropagation.injector
    fun internalOrder(name: String, number: String): String {
        Thread.sleep(number.toLong())
        return reportingServiceRestClient
            .get()
            .uri("http://reporting-service/dummy/$name/$number")
            .retrieve()
            .body(String::class.java)!!
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
        return "dummy.order"
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
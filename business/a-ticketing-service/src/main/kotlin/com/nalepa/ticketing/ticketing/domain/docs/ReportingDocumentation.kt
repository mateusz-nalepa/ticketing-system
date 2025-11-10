package com.nalepa.ticketing.ticketing.domain.docs

import io.micrometer.common.docs.KeyName
import io.micrometer.common.lang.NonNull
import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationConvention
import io.micrometer.observation.docs.ObservationDocumentation

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

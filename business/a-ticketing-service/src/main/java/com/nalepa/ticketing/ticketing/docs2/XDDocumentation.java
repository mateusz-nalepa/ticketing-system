package com.nalepa.ticketing.ticketing.docs2;

import io.micrometer.common.docs.KeyName;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;
import io.micrometer.observation.docs.ObservationDocumentation;
import org.jetbrains.annotations.NotNull;

enum XDDocumentation implements ObservationDocumentation {

    /**
     * REPORT It's alive!
     */
    REPORT {
        @Override
        public Class<? extends ObservationConvention<? extends Observation.Context>> getDefaultConvention() {
            return XDConvention.class;
        }

        @Override
        public KeyName @NotNull [] getLowCardinalityKeyNames() {
            return XDDocumentation.LowCardinalityKeyNames.values();
        }

        @Override
        public KeyName @NotNull [] getHighCardinalityKeyNames() {
            return XDDocumentation.HighCardinalityKeyNames.values();
        }
    };

    enum HighCardinalityKeyNames implements KeyName {
        /**
         * High XD
         */
        DUMMY_NUMBER {
            @Override
            public @NotNull String asString() {
                return "dummy.number";
            }
        }
    }

    enum LowCardinalityKeyNames implements KeyName {
        /**
         * Low XD
         */
        DUMMY_NAME {
            @Override
            public @NotNull String asString() {
                return "dummy.name";
            }
        },
    }
}

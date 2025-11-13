package com.nalepa.ticketing.ticketing.docs2;

import io.micrometer.common.KeyValues;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationConvention;

public class XDConvention implements ObservationConvention<XDContext> {
    @Override
    public KeyValues getLowCardinalityKeyValues(XDContext context) {
        return KeyValues.of(
                XDDocumentation.LowCardinalityKeyNames.DUMMY_NAME.withValue(context.nameXD)
        );
    }

    @Override
    public KeyValues getHighCardinalityKeyValues(XDContext context) {
        return KeyValues.of(
                XDDocumentation.HighCardinalityKeyNames.DUMMY_NUMBER.withValue(context.number)
        );
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return context instanceof XDContext;
    }

    @Override
    public String getName() {
        return "dummy.ticket";
    }
}

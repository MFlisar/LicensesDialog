
package com.michaelflisar.licenses;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public final class LicenseBusProvider
{
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);
    
    public static Bus getInstance()
    {
        return BUS;
    }

    private LicenseBusProvider()
    {
    }
}

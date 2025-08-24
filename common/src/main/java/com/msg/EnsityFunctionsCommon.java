package com.msg;

import com.msg.platform.EnsityFunctionsServices;

public class EnsityFunctionsCommon {

    public static void init() {

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", EnsityFunctionsServices.PLATFORM.getPlatformName(), EnsityFunctionsServices.PLATFORM.getEnvironmentName());

        if (EnsityFunctionsServices.PLATFORM.isModLoaded(Constants.ID)) {
            Constants.LOG.info("Running mod {}.", Constants.NAME);
        }
    }

}
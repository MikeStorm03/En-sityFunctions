package com.msg;

import com.msg.platform.Services;

public class CommonClass {

    public static void init() {

        Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());

        if (Services.PLATFORM.isModLoaded(Constants.ID)) {
            Constants.LOG.info("Running mod OldEnd.");
        }
    }

}
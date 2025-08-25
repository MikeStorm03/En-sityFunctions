package com.msg.ensity_functions;

import com.msg.ensity_functions.platform.Services;

public class CommonClass {

    public static void init() {

        Constants.LOG.info("Mod {} is running on {}! we are currently in a {} environment!",
										Constants.NAME,
										Services.PLATFORM.getPlatformName(),
										Services.PLATFORM.getEnvironmentName());
    }

}
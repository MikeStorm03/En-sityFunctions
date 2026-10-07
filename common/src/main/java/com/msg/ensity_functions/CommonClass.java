/*
En-sityFunctions
Copyright (C) 2025 - MikeStorm03

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/
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
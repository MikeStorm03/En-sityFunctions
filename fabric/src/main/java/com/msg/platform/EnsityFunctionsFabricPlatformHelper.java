package com.msg.platform;

import com.msg.platform.services.EnsityFunctionsPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class EnsityFunctionsFabricPlatformHelper implements EnsityFunctionsPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}

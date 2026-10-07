/*
En-sityFunctions
Copyright (C) 2025 - MikeStorm03

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/
package com.msg.ensity_functions;

import com.msg.ensity_functions.worldgen.densityfunction.LonelyIsland;
import com.msg.ensity_functions.worldgen.biome_source.NoMainBiomeSource;
import com.msg.ensity_functions.worldgen.densityfunction.FloatingIslands;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;

public class EnsityFunctions implements ModInitializer {

    @Override
    public void onInitialize() {

        //Constants.LOG.info("Hello Fabric world!");
        Registry.register(BuiltInRegistries.BIOME_SOURCE, Constants.resourcesLocation("no_main_end"), NoMainBiomeSource.CODEC);
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.resourcesLocation( "lonely_island"), LonelyIsland.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.resourcesLocation( "floating_islands"), FloatingIslands.CODEC.codec());

        FabricLoader.getInstance().getModContainer(Constants.ID).ifPresent(container -> {
        
            ResourceManagerHelperImpl.registerBuiltinResourcePack(Constants.resourcesLocation("lonely_end_island"),
                                                                                "data/msg/datapacks/lonely_end_island",
                                                                                container,
                                                                                Component.translatable("datapack.lonely_end_island"),
                                                                                ResourcePackActivationType.NORMAL);

            ResourceManagerHelperImpl.registerBuiltinResourcePack(Constants.resourcesLocation("no_main_island"),
                                                                                    "data/msg/datapacks/no_main_island",
                                                                                    container,
                                                                                    Component.translatable("datapack.no_main_island"),
                                                                                    ResourcePackActivationType.NORMAL);

		});

        CommonClass.init();
    }
}

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
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.fabric.impl.resource.ResourceLoaderImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;

public class EnsityFunctions implements ModInitializer {

    @Override
    public void onInitialize() {

        //Constants.LOG.info("Hello Fabric world!");
        Registry.register(BuiltInRegistries.BIOME_SOURCE, Constants.identifier("no_main_end"), NoMainBiomeSource.CODEC);
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.identifier( "lonely_island"), LonelyIsland.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.identifier( "floating_islands"), FloatingIslands.CODEC.codec());

        FabricLoader.getInstance().getModContainer(Constants.ID).ifPresent(container -> {
        
            ResourceLoaderImpl.registerBuiltinPack(Constants.identifier("lonely_end_island"),
                                                                                "data/msg/datapacks/lonely_end_island",
                                                                                container,
                                                                                Component.translatable("datapack.lonely_end_island"),
                                                                                PackActivationType.NORMAL);

            ResourceLoaderImpl.registerBuiltinPack(Constants.identifier("no_main_island"),
                                                                                    "data/msg/datapacks/no_main_island",
                                                                                    container,
                                                                                    Component.translatable("datapack.no_main_island"),
                                                                                    PackActivationType.NORMAL);

		});

        CommonClass.init();
    }
}

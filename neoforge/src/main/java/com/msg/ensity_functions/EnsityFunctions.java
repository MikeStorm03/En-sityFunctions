/*
En-sityFunctions
Copyright (C) 2025 - MikeStorm03

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/
package com.msg.ensity_functions;

import com.msg.ensity_functions.worldgen.biome_source.NoMainBiomeSource;
import com.msg.ensity_functions.worldgen.densityfunction.FloatingIslands;
import com.msg.ensity_functions.worldgen.densityfunction.LonelyIsland;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.Pack.Position;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.ID)
@EventBusSubscriber(modid = Constants.ID)
public class EnsityFunctions {

    public EnsityFunctions() {
        CommonClass.init();
    }

    @SubscribeEvent 
    public static void registerSetup(RegisterEvent event) {
        Registry<?> registry = event.getRegistry();
        if (registry.equals(BuiltInRegistries.BIOME_SOURCE)) Registry.register(BuiltInRegistries.BIOME_SOURCE, Constants.resourcesLocation("no_main_end"), NoMainBiomeSource.CODEC);
        else if (registry.equals(BuiltInRegistries.DENSITY_FUNCTION_TYPE)) {
            Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.resourcesLocation( "lonely_island"), LonelyIsland.CODEC.codec());
            Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.resourcesLocation( "floating_islands"), FloatingIslands.CODEC.codec());
        }
    }

    @SubscribeEvent 
    public static void builtInDataPack(final AddPackFindersEvent event) {
        event.addPackFinders(ResourceLocation.fromNamespaceAndPath(Constants.ID, "data/msg/datapacks/lonely_end_island"),
                            PackType.SERVER_DATA,
                            Component.translatable("datapack.lonely_end_island"),
                            PackSource.FEATURE,
                            false,
                            Position.TOP
                        );
        event.addPackFinders(ResourceLocation.fromNamespaceAndPath(Constants.ID, "data/msg/datapacks/no_main_island"),
                            PackType.SERVER_DATA,
                            Component.translatable("datapack.no_main_island"),
                            PackSource.FEATURE,
                            false,
                            Position.TOP
                        );
    }
}
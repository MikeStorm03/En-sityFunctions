/*
En-sityFunctions
Copyright (C) 2025 - MikeStorm03

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/
package com.msg.ensity_functions;

import java.nio.file.Path;
import java.util.Optional;

import com.msg.ensity_functions.worldgen.biome_source.NoMainBiomeSource;
import com.msg.ensity_functions.worldgen.densityfunction.FloatingIslands;
import com.msg.ensity_functions.worldgen.densityfunction.LonelyIsland;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.Pack.ResourcesSupplier;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Constants.ID)
@EventBusSubscriber(modid = Constants.ID)
public class EnsityFunctions {

    public EnsityFunctions() {
        CommonClass.init();
    }

    @SubscribeEvent
    public static void registerSetup(final RegisterEvent event) {
        Registry<?> registry = event.getVanillaRegistry();
        if (registry == null) return;
        if (registry.equals(BuiltInRegistries.BIOME_SOURCE)) Registry.register(BuiltInRegistries.BIOME_SOURCE, Constants.identifier("no_main_end"), NoMainBiomeSource.CODEC);
        else if (registry.equals(BuiltInRegistries.DENSITY_FUNCTION_TYPE)) {
            Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.identifier( "lonely_island"), LonelyIsland.CODEC.codec());
            Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, Constants.identifier( "floating_islands"), FloatingIslands.CODEC.codec());
        }
    }

    @SubscribeEvent 
    public static void builtInDataPack(final AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) return;
        IModFile modFileInfo = ModList.get().getModFileById(Constants.ID).getFile();
        event.addRepositorySource(consumer -> {
            consumer.accept(getPack(event, modFileInfo, "lonely_end_island"));
            consumer.accept(getPack(event, modFileInfo, "no_main_island"));
        });
    }

    private static Pack getPack(AddPackFindersEvent event, IModFile modFile, String packNamespace) {

        Path path = modFile.findResource("data/msg/datapacks/" + packNamespace);

        return Pack.readMetaAndCreate(

            new PackLocationInfo("msg:" + packNamespace,
                                Component.translatable("datapack." + packNamespace),
                                PackSource.FEATURE,
                                Optional.empty()
            ),

            new ResourcesSupplier() {
                @Override
                public PackResources openPrimary(PackLocationInfo loc) {
                    return new PathPackResources(loc, path);
                }

                @Override
                public PackResources openFull(PackLocationInfo loc, Pack.Metadata metadata) {
                    return new PathPackResources(loc, path);
                }
            },

            PackType.SERVER_DATA,
            new PackSelectionConfig(false, Position.TOP, false));

    }
}
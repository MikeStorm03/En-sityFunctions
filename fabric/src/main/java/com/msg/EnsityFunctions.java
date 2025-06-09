package com.msg;

import com.msg.worldgen.densityfunction.LonelyIsland;
import com.msg.worldgen.densityfunction.FloatingIslands;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class EnsityFunctions implements ModInitializer {
    
    @Override
    public void onInitialize() {

        //Constants.LOG.info("Hello Fabric world!");

        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "lonely_island"), LonelyIsland.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "floating_islands"), FloatingIslands.CODEC.codec());

        CommonClass.init();
    }
}

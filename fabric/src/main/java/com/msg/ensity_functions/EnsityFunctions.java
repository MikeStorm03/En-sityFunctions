package com.msg.ensity_functions;

import com.msg.ensity_functions.worldgen.densityfunction.LonelyIsland;
import com.msg.ensity_functions.worldgen.biome_source.NoMainBiomeSource;
import com.msg.ensity_functions.worldgen.densityfunction.FloatingIslands;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class EnsityFunctions implements ModInitializer {

    @Override
    public void onInitialize() {

        //Constants.LOG.info("Hello Fabric world!");
        Registry.register(BuiltInRegistries.BIOME_SOURCE, ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE,"no_main_end"), NoMainBiomeSource.CODEC);
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "lonely_island"), LonelyIsland.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "floating_islands"), FloatingIslands.CODEC.codec());
/*
        FabricLoader.getInstance().getModContainer(Constants.NAMESPACE).ifPresent(container -> {

			ResourceManagerHelper.registerBuiltinResourcePack(ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "lonely_end_island"), container,
                                    Component.literal("Built-in datapack.\nTurn the end into the Old End that only have 1 main island and the_end biome."),
                                    ResourcePackActivationType.NORMAL);

			registerBuiltinDataPack(ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "floating_islands"), container,
                                    ResourcePackActivationType.NORMAL);

		});
*/
        CommonClass.init();
    }
}

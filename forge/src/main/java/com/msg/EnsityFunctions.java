package com.msg;

import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;
import com.msg.worldgen.biome_source.NoMainBiomeSource;
import com.msg.worldgen.densityfunction.FloatingIslands;
import com.msg.worldgen.densityfunction.LonelyIsland;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(Constants.ID)
public class EnsityFunctions {

    // Density Function Register
    public static final DeferredRegister<MapCodec<? extends DensityFunction>> DENSITY_REGISTER = DeferredRegister.create(Registries.DENSITY_FUNCTION_TYPE, Constants.NAMESPACE);
    public static final Supplier<MapCodec<LonelyIsland>> LONELY_ISLAND = DENSITY_REGISTER.register("lonely_island", () -> MapCodec.unit(new LonelyIsland(0L)));
    public static final Supplier<MapCodec<FloatingIslands>> FLOATING_ISLANDS = DENSITY_REGISTER.register("floating_islands", () -> MapCodec.unit(new FloatingIslands(0L)));
    
    // Biome Source Register
    public static final DeferredRegister<MapCodec<? extends BiomeSource>> BIOME_SOURCE_REGISTER = DeferredRegister.create(Registries.BIOME_SOURCE, Constants.NAMESPACE);
    public static final Supplier<MapCodec<NoMainBiomeSource>> NO_MAIN_END = BIOME_SOURCE_REGISTER.register("no_main_end", () -> NoMainBiomeSource.CODEC);

    public EnsityFunctions() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(() -> {}));
        MinecraftForge.EVENT_BUS.register(this);

        DENSITY_REGISTER.register(eventBus);
        BIOME_SOURCE_REGISTER.register(eventBus);

        EnsityFunctionsCommon.init();
    }
}
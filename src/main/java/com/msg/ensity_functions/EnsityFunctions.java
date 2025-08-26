package com.msg.ensity_functions;

import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.serialization.MapCodec;
import com.msg.ensity_functions.worldgen.biome_source.NoMainBiomeSource;
import com.msg.ensity_functions.worldgen.densityfunction.FloatingIslands;
import com.msg.ensity_functions.worldgen.densityfunction.LonelyIsland;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(EnsityFunctions.ID)
public class EnsityFunctions {

	public static final String NAMESPACE = "msg";
	public static final String ID = "ensity_functions";
	public static final String NAME = "En-sityFunctions";
	public static final Logger LOG = LoggerFactory.getLogger(NAME);

    // Density Function Register
    public static final DeferredRegister<MapCodec<? extends DensityFunction>> DENSITY_REGISTER = DeferredRegister.create(Registries.DENSITY_FUNCTION_TYPE, NAMESPACE);
    public static final Supplier<MapCodec<LonelyIsland>> LONELY_ISLAND = DENSITY_REGISTER.register("lonely_island", () -> MapCodec.unit(new LonelyIsland(0L)));
    public static final Supplier<MapCodec<FloatingIslands>> FLOATING_ISLANDS = DENSITY_REGISTER.register("floating_islands", () -> MapCodec.unit(new FloatingIslands(0L)));
    
    // Biome Source Register
    public static final DeferredRegister<MapCodec<? extends BiomeSource>> BIOME_SOURCE_REGISTER = DeferredRegister.create(Registries.BIOME_SOURCE, NAMESPACE);
    public static final Supplier<MapCodec<NoMainBiomeSource>> NO_MAIN_END = BIOME_SOURCE_REGISTER.register("no_main_end", () -> NoMainBiomeSource.CODEC);

    public EnsityFunctions(FMLJavaModLoadingContext context) {

        LOG.info("Mod {} is running on Forge!", NAME);

        var modBusGroup = context.getModBusGroup();

        FMLCommonSetupEvent.getBus(modBusGroup).addListener((FMLCommonSetupEvent event) -> event.enqueueWork(() -> {}));

        DENSITY_REGISTER.register(modBusGroup);
        BIOME_SOURCE_REGISTER.register(modBusGroup);

    }
}
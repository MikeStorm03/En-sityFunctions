package com.msg;

import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;
import com.msg.worldgen.densityfunction.FloatingIslands;
import com.msg.worldgen.densityfunction.LonelyIsland;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.DensityFunction;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Constants.ID)
public class EnsityFunctions {
    
    //public static final DeferredRegister<MapCodec<? extends DensityFunction>> DENSITY_REGISTER = DeferredRegister.create(Registries.DENSITY_FUNCTION_TYPE, Constants.NAMESPACE);
    //public static final Supplier<MapCodec<LonelyIsland>> LONELY_ISLAND = DENSITY_REGISTER.register("lonely_island", () -> MapCodec.unit(new LonelyIsland(0L)));
    //public static final Supplier<MapCodec<FloatingIslands>> FLOATING_ISLANDS = DENSITY_REGISTER.register("floating_islands", () -> MapCodec.unit(new FloatingIslands(0L)));

    public EnsityFunctions(IEventBus eventBus) {

        //Constants.LOG.info("Hello NeoForge world!");
        //DENSITY_REGISTER.register(eventBus);
        CommonClass.init();

    }
}
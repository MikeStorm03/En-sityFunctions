package com.msg.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.msg.Constants;
import com.msg.worldgen.densityfunction.FloatingIslands;
import com.msg.worldgen.densityfunction.LonelyIsland;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunctions;

@Mixin(DensityFunctions.class)
public class DensityFunctionsMixin {
    @Inject(method = "bootstrap", at = @At("HEAD"), cancellable = false)
    private static void inject(CallbackInfoReturnable ci){
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "lonely_island"), LonelyIsland.CODEC.codec());
        Registry.register(BuiltInRegistries.DENSITY_FUNCTION_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.NAMESPACE, "floating_islands"), FloatingIslands.CODEC.codec());
    }
}

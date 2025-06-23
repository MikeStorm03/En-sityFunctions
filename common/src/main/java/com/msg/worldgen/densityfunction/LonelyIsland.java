package com.msg.worldgen.densityfunction;

import com.mojang.serialization.MapCodec;

import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

//import net.minecraft.world.level.levelgen.DensityFunctions.EndIslandDensityFunction;
//import net.minecraft.world.level.levelgen.DensityFunctions.BlendedNoise;

public final class LonelyIsland implements DensityFunction.SimpleFunction {
   public static final KeyDispatchDataCodec<LonelyIsland> CODEC = KeyDispatchDataCodec.of(MapCodec.unit(new LonelyIsland(0L)));
   private final SimplexNoise islandNoise;

   public LonelyIsland(long seed) {
      RandomSource randomSource = new LegacyRandomSource(seed);
      randomSource.consumeCount(17292);
      this.islandNoise = new SimplexNoise(randomSource);
   }

   private static float getHeightValue(SimplexNoise noise, int x, int z) {
      return Mth.clamp(100.0F - Mth.sqrt((float)(x * x + z * z)) * 8.0F, -100.0F, 80.0F);
   }

   public double compute(DensityFunction.FunctionContext context) {
      return ((double)getHeightValue(this.islandNoise, context.blockX() / 8, context.blockZ() / 8) - 8.0) / 128.0;
   }

   public double minValue() {
      return -0.84375;
   }

   public double maxValue() {
      return 0.5625;
   }

   public KeyDispatchDataCodec<? extends DensityFunction> codec() {
      return CODEC;
   }
}

package com.msg.worldgen.densityfunction;

import com.mojang.serialization.MapCodec;

import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public final class FloatingIslands implements DensityFunction.SimpleFunction {
   public static final KeyDispatchDataCodec<FloatingIslands> CODEC = KeyDispatchDataCodec.of(MapCodec.unit(new FloatingIslands(0L)));
   private static final float ISLAND_THRESHOLD = -0.9F;
   private final SimplexNoise islandNoise;

   public FloatingIslands(long seed) {
      RandomSource randomSource = new LegacyRandomSource(seed);
      randomSource.consumeCount(17292);
      this.islandNoise = new SimplexNoise(randomSource);
   }

   private static float getHeightValue(SimplexNoise noise, int x, int z) {
      int i = x / 2;   x %= 2;
      int j = z / 2;   z %= 2;
      float f = -100.0F;

      for(int m = -12; m <= 12; ++m) {
         for(int n = -12; n <= 12; ++n) {
            long o = (long)(i + m);
            long p = (long)(j + n);
            if (noise.getValue((double)o, (double)p) < -0.8999999761581421) {
               float g = (Mth.abs((float)o) * 3439.0F + Mth.abs((float)p) * 147.0F) % 13.0F + 9.0F;
               float h = (float)(x - m * 2);
               float q = (float)(z - n * 2);
               float r = 100.0F - Mth.sqrt(h * h + q * q) * g;
               r = Mth.clamp(r, -100.0F, 80.0F);
               f = Math.max(f, r);
            }
         }
      }

      return f;
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

package com.msg.worldgen.biome_source;

import java.util.stream.Stream;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;

public class NoMainBiomeSource extends BiomeSource {

    public static final MapCodec<NoMainBiomeSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(RegistryOps.retrieveElement(Biomes.END_HIGHLANDS), RegistryOps.retrieveElement(Biomes.END_MIDLANDS), RegistryOps.retrieveElement(Biomes.SMALL_END_ISLANDS), RegistryOps.retrieveElement(Biomes.END_BARRENS)).apply(instance, instance.stable(NoMainBiomeSource::new)));
    private final Holder<Biome> highlands;
    private final Holder<Biome> midlands;
    private final Holder<Biome> smallIslands;
    private final Holder<Biome> barrens;

    public static NoMainBiomeSource create(HolderGetter<Biome> biomeGetter) {
      return new NoMainBiomeSource(biomeGetter.getOrThrow(Biomes.END_HIGHLANDS), biomeGetter.getOrThrow(Biomes.END_MIDLANDS), biomeGetter.getOrThrow(Biomes.SMALL_END_ISLANDS), biomeGetter.getOrThrow(Biomes.END_BARRENS));
    }

    private NoMainBiomeSource(Holder<Biome> highlands, Holder<Biome> midlands, Holder<Biome> smallIslands, Holder<Biome> barrens) {
        this.highlands = highlands;
        this.midlands = midlands;
        this.smallIslands = smallIslands;
        this.barrens = barrens;
    }

    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return Stream.of(this.highlands, this.midlands, this.smallIslands, this.barrens);
    }

    protected MapCodec<? extends BiomeSource> codec() {
        return CODEC;
    }

    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler noise) {
        double d = noise.erosion().compute(new DensityFunction.SinglePointContext((SectionPos.blockToSectionCoord(QuartPos.toBlock(x)) * 2 + 1) * 8, QuartPos.toBlock(y), (SectionPos.blockToSectionCoord(QuartPos.toBlock(z)) * 2 + 1) * 8));
        if (d > 0.25) {
            return this.highlands;
        } else if (d >= -0.0625) {
            return this.midlands;
        } else {
            return d < -0.21875 ? this.smallIslands : this.barrens;
        }
    }
}

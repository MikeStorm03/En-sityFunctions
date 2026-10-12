/*
En-sityFunctions
Copyright (C) 2025 - MikeStorm03

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/
package com.msg.ensity_functions.worldgen.biome_source;

import java.util.stream.Stream;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
// import net.minecraft.world.level.biome.TheEndBiomeSource;

@Deprecated(forRemoval = true)
public class NoMainBiomeSource extends BiomeSource {
    public static final MapCodec<NoMainBiomeSource> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
                                                                                                    RegistryOps.retrieveElement(Biomes.END_HIGHLANDS),
                                                                                                    RegistryOps.retrieveElement(Biomes.END_MIDLANDS),
                                                                                                    RegistryOps.retrieveElement(Biomes.SMALL_END_ISLANDS),
                                                                                                    RegistryOps.retrieveElement(Biomes.END_BARRENS))
                                                                                                .apply(i, i.stable(NoMainBiomeSource::new)));
    private final Holder<Biome> highlands;
    private final Holder<Biome> midlands;
    private final Holder<Biome> islands;
    private final Holder<Biome> barrens;

    public static NoMainBiomeSource create(final HolderGetter<Biome> biomes) {
        return new NoMainBiomeSource(biomes.getOrThrow(Biomes.END_HIGHLANDS),
                                    biomes.getOrThrow(Biomes.END_MIDLANDS),
                                    biomes.getOrThrow(Biomes.SMALL_END_ISLANDS),
                                    biomes.getOrThrow(Biomes.END_BARRENS)
                                );
    }

    private NoMainBiomeSource(final Holder<Biome> highlands, final Holder<Biome> midlands, final Holder<Biome> islands, final Holder<Biome> barrens) {
        this.highlands = highlands;
        this.midlands = midlands;
        this.islands = islands;
        this.barrens = barrens;
    }

    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return Stream.of(this.highlands, this.midlands, this.islands, this.barrens);
    }

    protected MapCodec<NoMainBiomeSource> codec() {
        return CODEC;
    }

    public BiomeResolver createResolver(final Climate.Sampler sampler) {
        return (quartX, quartY, quartZ) -> this.getNoiseBiome(quartX, quartY, quartZ, sampler);
    }

    private Holder<Biome> getNoiseBiome(final int quartX, final int quartY, final int quartZ, final Climate.Sampler sampler) {

        double heightValue = sampler.erosion().sampleValue(
                                                        (SectionPos.blockToSectionCoord(QuartPos.toBlock(quartX)) * 2 + 1) * 8,
                                                        QuartPos.toBlock(quartY),
                                                        (SectionPos.blockToSectionCoord(QuartPos.toBlock(quartZ)) * 2 + 1) * 8
                                                    );
        if (heightValue > 0.25d) {
            return this.highlands;
        } else if (heightValue >= -0.0625d) {
            return this.midlands;
        } else {
            return heightValue < -0.21875d ? this.islands : this.barrens;
        }
    }
}
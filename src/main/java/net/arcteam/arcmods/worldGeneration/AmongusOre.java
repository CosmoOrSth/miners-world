package net.arcteam.arcmods.worldGeneration;

import java.util.Arrays;

import net.arcteam.arcmods.blocks.OreBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class AmongusOre {

    private static ConfiguredFeature<?, ?> ORE_CONFIGURED_FEATURE = new ConfiguredFeature(
        Feature.ORE, new OreFeatureConfig(
			OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
			OreBlocks.AMONGUS_ORE.getDefaultState(),
			4)); // vein size
	
    public static PlacedFeature ORE_PLACED_FEATURE = new PlacedFeature(
		RegistryEntry.of(ORE_CONFIGURED_FEATURE),
		Arrays.asList(
			CountPlacementModifier.of(2), // number of veins per chunk
			SquarePlacementModifier.of(), // spreading horizontally
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(19))
		)); // height

    public static void register() {

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
        new Identifier("minersworld", "amongus_ore"), ORE_CONFIGURED_FEATURE);
    	Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("minersworld", "amongus_ore"),
        	ORE_PLACED_FEATURE);
    	BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
        	RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            	new Identifier("minersworld", "amongus_ore")));
    }

}

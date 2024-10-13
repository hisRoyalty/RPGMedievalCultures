package com.hisroyalty.rmg.worldgen;

import com.hisroyalty.rmg.RMGMod;
import com.hisroyalty.rmg.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ConfiguredFeatures {
    // HEMATITE, LIMONITE, MAGNETITE, PYRITE, SIDERITE
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MAGNETITE_ORE_KEY = registerKey("magnetite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PYRITE_ORE_KEY = registerKey("pyrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LIMONITE_ORE_KEY = registerKey("limonite_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldLimoniteOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.LIMONITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_LIMONITE_ORE.get().defaultBlockState()));


        List<OreConfiguration.TargetBlockState> overworldMagnetiteOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.MAGNETITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_MAGNETITE_ORE.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldPyriteOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.PYRITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_PYRITE_ORE.get().defaultBlockState()));



        register(context, OVERWORLD_LIMONITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldLimoniteOres, 9));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(RMGMod.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}


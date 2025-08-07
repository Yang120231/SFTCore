package org.leodreamer.sftcore.common.data.recipe;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import net.minecraft.resources.ResourceLocation;
import org.leodreamer.sftcore.SFTCore;

import java.util.Set;

public final class SFTOres {
    //
    //
    // public static final GTOreDefinition SURFACE_VEIN = create(
    //         SFTCore.id("surface_vein"),
    //         vein -> vein.weight(100)
    //                 .clusterSize(UniformInt.of(28, 35))
    //                 .density(0.25f)
    //                 .layer(WorldGenLayers.STONE)
    //                 .biomes(BiomeTags.IS_OVERWORLD)
    //                 .heightRangeUniform(0, 40)
    //                 .layeredVeinGenerator(generator -> GTLayerPattern.builder(GTOres.OVERWORLD_RULES)
    //                         .layer((l) -> l.weight(2).state(Blocks.COPPER_ORE.defaultBlockState()).size(2, 4))
    //                         .layer(l -> l.weight(3).state(Blocks.COAL_ORE.defaultBlockState()).size(2, 3))
    //                         .layer(l -> l.weight(2).state(Blocks.IRON_ORE.defaultBlockState()).size(2, 3))
    //                         .layer(l -> l.weight(1).state(Blocks.LAPIS_ORE.defaultBlockState()).size(1, 1))
    //                         .build())
    // );
    //
    // public static final GTOreDefinition DEEP_VEIN = create(
    //         SFTCore.id("deep_vein"),
    //         vein -> vein.weight(100)
    //                 .clusterSize(UniformInt.of(28, 35))
    //                 .density(0.3f)
    //                 .layer(WorldGenLayers.DEEPSLATE)
    //                 .biomes(BiomeTags.IS_OVERWORLD)
    //                 .heightRangeUniform(-60, 0)
    //                 .layeredVeinGenerator(generator -> GTLayerPattern.builder(GTOres.DEEPSLATE_RULES)
    //                         .layer(l -> l.weight(2).state(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState()).size(1, 5))
    //                         .layer(l -> l.weight(2).state(Blocks.DEEPSLATE_REDSTONE_ORE.defaultBlockState()).size(2, 4))
    //                         .layer(l -> l.weight(2).state(Blocks.DEEPSLATE_GOLD_ORE.defaultBlockState()).size(1, 3))
    //                         .layer(l -> l.weight(1).state(Blocks.DEEPSLATE_LAPIS_ORE.defaultBlockState()).size(1, 1))
    //                         .layer(l -> l.weight(1).state(Blocks.DEEPSLATE_DIAMOND_ORE.defaultBlockState()).size(1, 1))
    //                         .build())
    // );
    //
    // public static RuleTest[] SGT_NETHER_RULES = new RuleTest[]{
    //         new BlockMatchTest(Blocks.NETHERRACK)
    // };
    //
    // public static final GTOreDefinition NETHER_VEIN = create(
    //         SFTCore.id("nether_vein"),
    //         vein -> vein.weight(60)
    //                 .clusterSize(UniformInt.of(32, 40))
    //                 .density(0.25f)
    //                 .layer(WorldGenLayers.NETHERRACK)
    //                 .biomes(BiomeTags.IS_NETHER)
    //                 .heightRangeUniform(5, 90)
    //                 .layeredVeinGenerator(generator -> GTLayerPattern.builder(SGT_NETHER_RULES)
    //                         .layer((l) -> l.weight(5).state(Blocks.NETHER_QUARTZ_ORE.defaultBlockState()).size(3, 5))
    //                         .layer(l -> l.weight(3).state(Blocks.NETHER_GOLD_ORE.defaultBlockState()).size(2, 4))
    //                         .build())
    // );


    public static void init() {
        // Remove all the veins
        Set<ResourceLocation> keys = Set.copyOf(GTRegistries.ORE_VEINS.keys());
        keys.forEach((rl) -> {
            SFTCore.LOGGER.info("Removing ore vein: {}", rl);
            GTRegistries.ORE_VEINS.remove(rl);
        });
    }
}

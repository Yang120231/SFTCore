package org.leodreamer.sftcore.common.data.recipes.misc;

import appeng.core.definitions.AEItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import gripe._90.megacells.definition.MEGAItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.leodreamer.sftcore.SFTCore;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.MV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static org.leodreamer.sftcore.common.data.recipes.SFTRecipeTypes.CERTUS_QUARTZ_CHARGE_RECIPES;
import static org.leodreamer.sftcore.common.data.recipes.SFTRecipeTypes.LARGE_INSCRIBER;

public final class AE2Recipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CERTUS_QUARTZ_CHARGE_RECIPES.recipeBuilder("certus_quartz_charge")
                .inputItems(AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), 32)
                .inputFluids(GTMaterials.Water.getFluid(1000))
                .outputItems(AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), 32)
                .duration(50)
                .EUt(VA[MV])
                .save(provider);

        inscribe(provider, "calculation", AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), AEItems.CALCULATION_PROCESSOR.asItem());
        inscribe(provider, "logic", Items.GOLD_INGOT, AEItems.LOGIC_PROCESSOR.asItem());
        inscribe(provider, "engineering", Items.DIAMOND, AEItems.ENGINEERING_PROCESSOR.asItem());
        inscribe(provider, "accumulation", MEGAItems.SKY_STEEL_INGOT.asItem(), MEGAItems.ACCUMULATION_PROCESSOR.asItem());
    }

    private static void inscribe(Consumer<FinishedRecipe> provider, String id, Item ingredient, Item processor) {
        LARGE_INSCRIBER.recipeBuilder(SFTCore.id("inscriber/" + id))
                .inputItems(ingredient, 16)
                .inputItems(AEItems.SILICON.asItem(), 12)
                .inputFluids(GTMaterials.Redstone.getFluid(16 * GTValues.L))
                .outputItems(processor, 16)
                .duration(100)
                .EUt(VA[MV])
                .save(provider);
    }
}

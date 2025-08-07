package org.leodreamer.sftcore.common.data.recipe.misc;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.Tags;
import org.leodreamer.sftcore.SFTCore;
import org.leodreamer.sftcore.common.data.SFTItems;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.ULV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.common.data.GTMaterials.Lava;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.CHEMICAL_BATH_RECIPES;

public class CommonGTRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        CHEMICAL_BATH_RECIPES.recipeBuilder(SFTCore.id("uu_matter"))
                .outputItems(SFTItems.UU_MATTER)
                .inputItems(Tags.Items.COBBLESTONE, 2)
                .inputFluids(Lava.getFluid(100))
                .duration(20)
                .EUt(VA[ULV])
                .save(provider);
    }
}

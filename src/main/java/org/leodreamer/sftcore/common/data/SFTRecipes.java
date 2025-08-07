package org.leodreamer.sftcore.common.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;
import org.leodreamer.sftcore.common.data.recipe.misc.AE2Recipes;
import org.leodreamer.sftcore.common.data.recipe.misc.MekanismRecipes;
import org.leodreamer.sftcore.common.data.recipe.misc.SemiconductorRecipes;

import java.util.function.Consumer;

public class SFTRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        SemiconductorRecipes.init(provider);
        MekanismRecipes.init(provider);
        AE2Recipes.init(provider);
    }
}

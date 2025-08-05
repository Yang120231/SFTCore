package org.leodreamer.sftcore.common.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import org.leodreamer.sftcore.common.data.recipes.misc.AE2Recipes;
import org.leodreamer.sftcore.common.data.recipes.misc.SemiconductorRecipes;

import java.util.function.Consumer;

public class SFTRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        SemiconductorRecipes.init(provider);
        AE2Recipes.init(provider);
    }
}

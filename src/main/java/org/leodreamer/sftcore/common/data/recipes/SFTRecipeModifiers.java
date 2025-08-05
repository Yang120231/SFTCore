package org.leodreamer.sftcore.common.data.recipes;

import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;

public final class SFTRecipeModifiers {

    public static final OverclockingLogic HALF_PERFECT_OVERCLOCK = OverclockingLogic.create(0.33, 4.0, false);
    public static final RecipeModifier OC_HALF_PERFECT =
            GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(HALF_PERFECT_OVERCLOCK);

    public static final OverclockingLogic HALF_PERFECT_OVERCLOCK_SUBTICK = OverclockingLogic.create(0.33, 4.0, true);
    public static final RecipeModifier OC_HALF_PERFECT_SUBTICK =
            GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(HALF_PERFECT_OVERCLOCK_SUBTICK);

}

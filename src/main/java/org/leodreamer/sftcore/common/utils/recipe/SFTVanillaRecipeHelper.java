package org.leodreamer.sftcore.common.utils.recipe;

import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import org.leodreamer.sftcore.SFTCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class SGTVanillaRecipeHelper {
    public static class ShapedRecipeBuilder {
        Consumer<FinishedRecipe> provider;
        String id;
        boolean setMaterialInfoData = true;
        ItemStack output;
        ArrayList<Object> recipe = new ArrayList<>();

        public ShapedRecipeBuilder(Consumer<FinishedRecipe> provider) {
            this.provider = provider;
        }

        public ShapedRecipeBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ShapedRecipeBuilder setMaterialInfoData(boolean setMaterialInfoData) {
            this.setMaterialInfoData = setMaterialInfoData;
            return this;
        }

        public ShapedRecipeBuilder pattern(String... patterns) {
            if (!recipe.isEmpty()) {
                SFTCore.LOGGER.warn("Recipe pattern is bad. Previous patterns: {}", recipe);
            }
            recipe.addAll(Arrays.asList(patterns));
            return this;
        }

        public ShapedRecipeBuilder arg(char sign, Object obj) {
            recipe.add(sign);
            recipe.add(obj);
            return this;
        }

        public void outputAndFinish(ItemStack output) {
            this.output = output;
            if (id == null) {
                SFTCore.LOGGER.warn("Recipe ID is not set for {}", output);
            }
            VanillaRecipeHelper.addShapedRecipe(provider, setMaterialInfoData, SFTCore.id(id), output, recipe.toArray());
        }
    }

    public static ShapedRecipeBuilder addShapedRecipe(Consumer<FinishedRecipe> provider, String id) {
        return new ShapedRecipeBuilder(provider).id(id);
    }
}

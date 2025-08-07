package org.leodreamer.sftcore.common.data.recipe.misc;

import appeng.core.definitions.AEItems;
import com.simibubi.create.AllItems;
import mekanism.common.Mekanism;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.leodreamer.sftcore.common.data.SFTItems;
import org.leodreamer.sftcore.common.data.SFTRecipes;
import org.leodreamer.sftcore.common.utils.recipe.SFTVanillaRecipeHelper;

import java.util.Objects;
import java.util.function.Consumer;

public final class VanillaRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        vanillaRecipes(provider);
        uuMatterRecipes(provider);
    }

    private static void vanillaRecipes(Consumer<FinishedRecipe> provider) {
        SFTVanillaRecipeHelper.addShapedRecipe("nether_star")
                .pattern("HHH", "SSS", " S ")
                .arg('H', Items.WITHER_SKELETON_SKULL)
                .arg('S', Items.SOUL_SAND)
                .output(new ItemStack(Items.NETHER_STAR))
                .save(provider);
    }

    private static void uuMatterRecipes(Consumer<FinishedRecipe> provider) {
        uu(provider, Items.COAL, 2, "   ", " U ", "   ");
        uu(provider, Items.IRON_INGOT, 3, " U ", " U ", " U ");
        uu(provider, Items.GOLD_INGOT, 3, "   ", "   ", "UUU");
        uu(provider, Items.COPPER_INGOT, 6, "  U", "   ", "U  ");
        uu(provider, Items.REDSTONE, 12, "   ", "U U", "   ");
        uu(provider, Items.GLOWSTONE_DUST, 4, "U  ", "  U", "   ");
        uu(provider, Items.LAPIS_LAZULI, 16, "  U", "  U", "U  ");
        uu(provider, Items.QUARTZ, 8, "  U", "  U", "U  ");
        uu(provider, Items.EMERALD, 2, "UUU", "   ", "UUU");
        uu(provider, Items.DIAMOND, 1, "U U", " U ", "U U");
        uu(provider, Items.NETHERITE_SCRAP, 1, "UUU", "U U", "UUU");

        uu(provider, AllItems.ZINC_INGOT.asItem(), 3, "U  ", " U ", "  U");

        var mek = Mekanism.MODID;
        uu(provider, Objects.requireNonNull(SFTRecipes.getItemById(mek, "ingot_osmium")), 3,
                " U ", "  U", "U  ");
        uu(provider, Objects.requireNonNull(SFTRecipes.getItemById(mek, "ingot_lead")), 3,
                "   ", " U ", "U U");
        uu(provider, Objects.requireNonNull(SFTRecipes.getItemById(mek, "ingot_tin")), 4,
                "   ", "U U", " U ");
        uu(provider, Objects.requireNonNull(SFTRecipes.getItemById(mek, "ingot_uranium")), 10,
                " U ", "UUU", "   ");
        uu(provider, Objects.requireNonNull(SFTRecipes.getItemById(mek, "fluorite_gem")), 8,
                "   ", "UUU", " U ");

        uu(provider, AEItems.SKY_DUST.asItem(), 6, "  U", " U ", "  U");
    }

    private static void uu(Consumer<FinishedRecipe> provider, Item output, int amount, String... pattern) {
        var name = output.getDescriptionId().replace('.', '_');
        SFTVanillaRecipeHelper.addShapedRecipe("uu/" + name)
                .pattern(pattern)
                .arg('U', SFTItems.UU_MATTER)
                .output(new ItemStack(output, amount))
                .save(provider);
    }
}

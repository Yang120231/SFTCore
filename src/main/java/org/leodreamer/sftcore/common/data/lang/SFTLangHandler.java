package org.leodreamer.sftcore.common.data.lang;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import org.leodreamer.sftcore.SFTCore;

public class SFTLangHandler extends LangHandler {
    public static void init(RegistrateLangProvider provider) {
        // recipe types autogen
        // I wonder why gt cannot generate them for me...
        for (var k : GTRegistries.RECIPE_TYPES.keys()) {
            if (k.getNamespace().equals(SFTCore.MOD_ID)) {
                provider.add("%s.%s".formatted(SFTCore.MOD_ID, k.getPath()),
                        RegistrateLangProvider.toEnglishName(k.getPath()));
            }
        }
        //// Cover the tooltip of GTCEu ////

        provider.add("gtceu.multiblock.parallelizable.tooltip", "§co §lParallelizable§r");
        provider.add("gtceu.part_sharing.disabled", "§e- Sharing: §4✘§r");
        provider.add("gtceu.part_sharing.enabled", "§e- Sharing: §a✔§r");
        provider.add("gtceu.machine.available_recipe_map_1.tooltip", "§5Δ §lRecipe Types: %s");
        provider.add("gtceu.machine.available_recipe_map_2.tooltip", "§5Δ §lRecipe Types: %s, %s");
        provider.add("gtceu.machine.available_recipe_map_3.tooltip", "§5Δ §lRecipe Types: %s, %s, %s");
        provider.add("gtceu.machine.available_recipe_map_4.tooltip", "§5Δ §lRecipe Types: %s, %s, %s, %s");

        // machines
        provider.add("gtceu.machine.parallel_hatch_mk5.tooltip", "Allows to run up to 64 recipes in parallel.");
        provider.add("gtceu.machine.parallel_hatch_mk6.tooltip", "Allows to run up to 256 recipes in parallel.");
        provider.add("gtceu.machine.parallel_hatch_mk7.tooltip", "Allows to run up to 1024 recipes in parallel.");
        provider.add("gtceu.machine.parallel_hatch_mk8.tooltip", "Allows to run up to 4096 recipes in parallel.");
        multiLang(provider, "gtceu.machine.electric_blast_furnace.tooltip",
                "§2- §lBlast Furnace Coil Bonus: §7§oFor every voltage tier above §bMV§7§o, temperature is increased by §r100K.",
                "§7§o   For every §f900K§7§o above the recipe temperature, energy consumption is reduced by §f5%%.§r",
                "§7§o   For every §f1800K§7§o above the recipe temperature, one perfect overclock is granted.§r");
        provider.add("gtceu.machine.cracker.tooltip.1", "- §7For every 1 level above §6Cupronickel§7, recipe energy consumption is reduced by 10%%");

        //// tooltips by SFT ////

        // recipe modifiers
        provider.add("sftcore.multiblock.half_perfect_overclock.tooltip", "§do §lHalf Perfect Overclock§r");
        provider.add("sftcore.multiblock.half_perfect_overclock.tooltip.1", "§7§o   Process 3 times faster when overclocked with 4 times power§r");
        provider.add("sftcore.multiblock.perfect_overclock.tooltip", "§6o §lPerfect Overclock§r");
        provider.add("sftcore.multiblock.perfect_overclock.tooltip.1", "§7§o   Increase power casually without increasing total energy consumption§r");
        provider.add("sftcore.multiblock.energy_multiplier.tooltip", "§a- §lEnergy Multiplier§r§a: %f§r");
        provider.add("sftcore.multiblock.time_multiplier.tooltip", "§9- §lTime Multiplier§r§9: %f§r");
        provider.add("sftcore.multiblock.mega_reduce_with_coil", "§co §lCoil Discount§r");
        provider.add("sftcore.multiblock.mega_reduce_with_coil.1", "§7§o   For every §d%dK§7§o above coil temperature, recipe energy is multiplied by §a%f§r§7§o and time by §9%f§r");
        provider.add("sftcore.machine.modified_by_sft", "§b§n§o- Modified By SFT§r§r§r§b§n -§r§r");

        // machines
        provider.add("sftcore.machine.desulfurizer.tooltip", "Desulfurize oil efficiently.");
        provider.add("sftcore.machine.oil_drilling_rig.tooltip", "Oh, It's not so environmental friendly...");
        provider.add("sftcore.machine.semiconductor_blast_furnace.tooltip", "Expert in producing semiconductor.");
        provider.add("sftcore.machine.large_inscriber.tooltip", "A large inscriber for advanced circuits.");
        provider.add("sftcore.machine.greenhouse.tooltip", "Hope our plants can grow well.");
        provider.add("sftcore.machine.certus_quartz_charger.tooltip", "Release the power of Certus Quartz.");
        provider.add("sftcore.machine.large_mekanism_nuclear_reactor.tooltip", "Extract energy from fuel thoroughly.");
        provider.add("sftcore.machine.large_cracker.tooltip.1", "- §7For every 1 level above §6Cupronickel§7, recipe energy and time consumption are both reduced by 10%%");
    }
}

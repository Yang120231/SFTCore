package org.leodreamer.sftcore.common.data;

import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static org.leodreamer.sftcore.SFTCore.REGISTRATE;

public final class SFTItems {
    public static final ItemEntry<Item> ULV_UNIVERSAL_CIRCUIT = REGISTRATE.item("ulv_universal_circuit", Item::new)
            .lang("ULV Universal Circuit").tag(CustomTags.ULV_CIRCUITS).register();

    public static final ItemEntry<Item> LV_UNIVERSAL_CIRCUIT = REGISTRATE.item("lv_universal_circuit", Item::new)
            .lang("LV Universal Circuit").tag(CustomTags.LV_CIRCUITS).register();

    public static final ItemEntry<Item> MV_UNIVERSAL_CIRCUIT = REGISTRATE.item("mv_universal_circuit", Item::new)
            .lang("MV Universal Circuit").tag(CustomTags.MV_CIRCUITS).register();

    public static final ItemEntry<Item> HV_UNIVERSAL_CIRCUIT = REGISTRATE.item("hv_universal_circuit", Item::new)
            .lang("HV Universal Circuit").tag(CustomTags.HV_CIRCUITS).register();

    public static final ItemEntry<Item> EV_UNIVERSAL_CIRCUIT = REGISTRATE.item("ev_universal_circuit", Item::new)
            .lang("EV Universal Circuit").tag(CustomTags.EV_CIRCUITS).register();

    public static final ItemEntry<Item> IV_UNIVERSAL_CIRCUIT = REGISTRATE.item("iv_universal_circuit", Item::new)
            .lang("IV Universal Circuit").tag(CustomTags.IV_CIRCUITS).register();

    public static final ItemEntry<Item> LuV_UNIVERSAL_CIRCUIT = REGISTRATE.item("luv_universal_circuit", Item::new)
            .lang("LuV Universal Circuit").tag(CustomTags.LuV_CIRCUITS).register();

    public static final ItemEntry<Item> ZPM_UNIVERSAL_CIRCUIT = REGISTRATE.item("zpm_universal_circuit", Item::new)
            .lang("ZPM Universal Circuit").tag(CustomTags.ZPM_CIRCUITS).register();

    public static final ItemEntry<Item> UV_UNIVERSAL_CIRCUIT = REGISTRATE.item("uv_universal_circuit", Item::new)
            .lang("UV Universal Circuit").tag(CustomTags.UV_CIRCUITS).register();

    public static final ItemEntry<Item> UHV_UNIVERSAL_CIRCUIT = REGISTRATE.item("uhv_universal_circuit", Item::new)
            .lang("UHV Universal Circuit").tag(CustomTags.UHV_CIRCUITS).register();

    public static void init() {
    }
}

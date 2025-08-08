package org.leodreamer.sftcore.forge;

import com.gregtechceu.gtceu.GTCEu;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.leodreamer.sftcore.common.command.SFTCommands;

@Mod.EventBusSubscriber(modid = GTCEu.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventListener {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        SFTCommands.register(event);
    }
}

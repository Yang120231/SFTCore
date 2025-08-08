package org.leodreamer.sftcore.common.command;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.leodreamer.sftcore.common.command.dump.DumpCommand;

public class SFTCommands {

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        DumpCommand.register(event.getDispatcher());
    }
}

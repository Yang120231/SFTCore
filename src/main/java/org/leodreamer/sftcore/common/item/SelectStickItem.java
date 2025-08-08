package org.leodreamer.sftcore.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;
import org.leodreamer.sftcore.common.command.dump.DumpCommand;

public class SelectStickItem extends Item {

    public SelectStickItem() {
        super(new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        BlockPos pos = context.getClickedPos();
        String posStr = pos.getX() + " " + pos.getY() + " " + pos.getZ();
        if (!player.isSecondaryUseActive()) {
            DumpCommand.SelectedData.setSelectedPos1(player, pos);
            if (context.getLevel().isClientSide)
                player.sendSystemMessage(Component.translatable("item.sftcore.dump.select_stick.first", posStr));
        } else {
            DumpCommand.SelectedData.setSelectedPos2(player, pos);
            if (context.getLevel().isClientSide)
                player.sendSystemMessage(Component.translatable("item.sftcore.dump.select_stick.second", posStr));
        }
        return InteractionResult.PASS;
    }
}

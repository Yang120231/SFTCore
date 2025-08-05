package org.leodreamer.sftcore.common.utils.dump.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraftforge.registries.ForgeRegistries;
import org.leodreamer.sftcore.Config;
import org.leodreamer.sftcore.common.utils.dump.item.SelectedData;
import org.leodreamer.sftcore.common.utils.dump.loggers.BaseDump;
import org.leodreamer.sftcore.common.utils.dump.loggers.BlockDump;
import org.leodreamer.sftcore.common.utils.dump.loggers.FluidDump;
import org.leodreamer.sftcore.common.utils.dump.loggers.ItemDump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DumpCommand {

    public static final String FOLDER = "dumps";
    public static final String ID_FILENAME = "vocabulary.json";
    public static final String MULTI_BLOCK_FILENAME = "multiBlock.txt";
    private static final Dynamic2CommandExceptionType ERROR_AREA_TOO_LARGE =
            new Dynamic2CommandExceptionType((limit, actual) -> Component.translatable(
                    "commands.fill.toobig", limit, actual));

    private static final Logger LOGGER = LoggerFactory.getLogger(DumpCommand.class);

    public static void
    register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("sftcore").then(
                        Commands.literal("dump")
                                .executes(context -> dumpIdentifiers(context.getSource(), Mode.ALL))
                                .then(Commands.literal("items")
                                        .executes(context -> dumpIdentifiers(context.getSource(), Mode.ITEM)))
                                .then(Commands.literal("blocks")
                                        .executes(context -> dumpIdentifiers(context.getSource(), Mode.BLOCK)))
                                .then(Commands.literal("fluid")
                                        .executes(context -> dumpIdentifiers(context.getSource(), Mode.FLUID)))
                                .then(Commands.literal("multiblock")
                                        .executes(context -> dumpMultiBlocks(context.getSource())))));
    }

    private static int dumpIdentifiers(CommandSourceStack stack, Mode mode) {
        return write(stack, ID_FILENAME, getJSONString(mode));
    }

    private static int dumpMultiBlocks(CommandSourceStack stack) throws CommandSyntaxException {
        Player player = stack.getPlayer();
        if (player == null) return 0;
        SelectedData.SelectedArea area = SelectedData.getSelectedArea(player);
        BoundingBox box = BoundingBox.fromCorners(area.pos1, area.pos2);

        int i = box.getXSpan() * box.getYSpan() * box.getZSpan();
        int j = stack.getLevel().getGameRules().getInt(GameRules.RULE_COMMAND_MODIFICATION_BLOCK_LIMIT);
        if (i > j)
            throw ERROR_AREA_TOO_LARGE.create(j, i);
        return write(stack, MULTI_BLOCK_FILENAME, getMultiblockString(stack, box));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static int write(CommandSourceStack stack, String filename, String content) {
        File file = new File(FOLDER);
        if (!file.exists())
            file.mkdirs();
        String filePath = FOLDER + File.separator + filename;
        stack.sendSystemMessage(Component.translatable("commands.sftcore.dump.start"));
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            Component component = Component.translatable("commands.sftcore.dump.success.link")
                    .withStyle(ChatFormatting.UNDERLINE)
                    .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, filePath)));
            stack.sendSuccess(() -> Component.translatable("commands.sftcore.dump.success")
                    .append(Component.literal(" "))
                    .append(component), true);
            return 1;
        } catch (IOException e) {
            stack.sendFailure(Component.translatable("commands.sftcore.dump.failure"));
            return 0;
        }
    }

    private static String getJSONString(Mode mode) {
        Map<String, Map<String, List<String>>> res = new LinkedHashMap<>();
        // Everything in Minecraft
        List<BaseDump> dumps = new ArrayList<>();

        if (mode == Mode.ALL || mode == Mode.BLOCK)
            dumps.add(new BlockDump());
        if (mode == Mode.ALL || mode == Mode.ITEM)
            dumps.add(new ItemDump());
        if (mode == Mode.ALL || mode == Mode.FLUID)
            dumps.add(new FluidDump());


        for (BaseDump dump : dumps) {
            res.put(dump.getTypeName(), dump.getIdentifierMap());
            res.put(dump.getTypeName() + "Tags", dump.getTagMap());
        }

        // remove the repeated items in blocks
        if (mode == Mode.ALL && Config.keepDistinct) {
            Map<String, List<String>> blockMap = res.get("Block");
            Map<String, List<String>> itemMap = res.get("Item");
            for (String namespace : blockMap.keySet()) {
                List<String> blockList = blockMap.get(namespace);
                List<String> itemList = itemMap.get(namespace);
                if (itemList != null && blockList != null)
                    itemList.removeAll(blockList);
            }
        }

        // write it as a JSON
        StringBuilder sb = new StringBuilder("{");
        for (String typeKey : res.keySet()) {
            sb.append("\"").append(typeKey).append("\": {");
            Map<String, List<String>> map = res.get(typeKey);
            for (String namespace : map.keySet()) {
                sb.append("\"").append(namespace).append("\":");
                List<String> list = map.get(namespace);
                sb.append(list.stream().map(name -> "\"" + name + "\"").toList()).append(",");
            }
            if (!map.isEmpty())
                sb.deleteCharAt(sb.length() - 1);
            sb.append("},");
        }
        if (!res.isEmpty())
            sb.deleteCharAt(sb.length() - 1);
        return sb.append("}").toString();
    }

    private static String getMultiblockString(CommandSourceStack stack, BoundingBox box) {
        // alias map: name -> a letter
        Map<String, Character> aliasMap = new HashMap<>();
        char[][][] blocks = new char[box.getXSpan()][box.getYSpan()][box.getZSpan()];

        // record names
        char current = 'A';
        for (int x = box.minX(); x <= box.maxX(); x++) {
            for (int y = box.minY(); y <= box.maxY(); y++) {
                for (int z = box.minZ(); z <= box.maxZ(); z++) {
                    Block block = stack.getLevel().getBlockState(new BlockPos(x, y, z)).getBlock();
                    String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString();
                    if (name.equals("minecraft:air")) continue;
                    if (!aliasMap.containsKey(name))
                        aliasMap.put(name, current++);
                    blocks[x - box.minX()][y - box.minY()][z - box.minZ()] = aliasMap.get(name);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // write the map
        for (String name : aliasMap.keySet())
            sb.append(aliasMap.get(name)).append(":\"").append(name).append("\",\n");
        if (!aliasMap.isEmpty()) {
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\n");
        }
        // write the array
        for (int x = 0; x < box.getXSpan(); x++) {
            sb.append("[");
            for (int y = 0; y < box.getYSpan(); y++) {
                sb.append("\"");
                for (int z = 0; z < box.getZSpan(); z++)
                    sb.append(blocks[x][y][z]);
                sb.append("\",");
            }
            sb.deleteCharAt(sb.length() - 1).append("]\n");
        }

        return sb.toString();
    }


    private enum Mode {
        ALL, BLOCK, ITEM, FLUID
    }
}

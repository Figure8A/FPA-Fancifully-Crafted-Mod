package com.figure8.commands;

import com.figure8.util.IEntityDataSaver;
import com.figure8.util.SquiggleAdd;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

public class SquiggleSet {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("squiggles")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("add").then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager
                        .argument("targets", EntityArgumentType.players())
                        .then((ArgumentBuilder<ServerCommandSource, ?>)(((RequiredArgumentBuilder)CommandManager
                                .argument("amount", IntegerArgumentType.integer())
                                .executes(context -> SquiggleSet.executeAdd((ServerCommandSource)context.getSource(), EntityArgumentType
                                        .getPlayers(context, "targets"), IntegerArgumentType
                                        .getInteger(context, "amount")))))))));
    }


    private static int executeAdd(ServerCommandSource source, Collection<? extends ServerPlayerEntity> targets, int amount) {
        for (ServerPlayerEntity serverPlayerEntity : targets) {
            SquiggleAdd.addSquiggles(((IEntityDataSaver) serverPlayerEntity), amount);
        }
        if (targets.size() == 1) {
            source.sendFeedback(() -> Text.literal("Set Squiggle Amount " + amount), true);
        } else {
            source.sendFeedback(() -> Text.literal("Set Squiggle Amounts " + amount), true);
        }
        return targets.size();
    }

}

package com.figure8.Networktests;

import com.figure8.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class ServerEntityWorldChangeEventsHandler implements ServerEntityWorldChangeEvents.AfterPlayerChange {


    @Override
    public void afterChangeWorld(ServerPlayerEntity serverPlayerEntity, ServerWorld origin, ServerWorld destination) {
        ((IEntityDataSaver) serverPlayerEntity).getPersistentData().getInt("squiggles");
    }
}

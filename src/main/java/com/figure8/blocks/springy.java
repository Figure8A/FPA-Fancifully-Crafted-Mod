package com.figure8.blocks;

import net.minecraft.util.StringIdentifiable;

public enum springy implements StringIdentifiable
{
    CROUCH("crouch"),
    CROUCH1("crouch1"),
    CROUCH2("crouch2"),
    CROUCHF("crouchf");

    private final String name;

    springy(String name) {
        this.name = name;

    }

    @Override
    public String asString() {
        return this.name;
    }

}
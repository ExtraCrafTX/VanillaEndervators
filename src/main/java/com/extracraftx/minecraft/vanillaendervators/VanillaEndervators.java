package com.extracraftx.minecraft.vanillaendervators;

import com.extracraftx.minecraft.vanillaendervators.blocks.Blocks;

import net.fabricmc.api.ModInitializer;

public class VanillaEndervators implements ModInitializer{

    @Override
    public void onInitialize() {
        Blocks.registerBlocks();
    }

}
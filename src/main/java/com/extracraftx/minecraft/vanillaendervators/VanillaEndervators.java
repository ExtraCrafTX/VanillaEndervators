package com.extracraftx.minecraft.vanillaendervators;

import com.extracraftx.minecraft.vanillaendervators.blocks.Blocks;
import com.extracraftx.minecraft.vanillaendervators.config.Config;

import net.fabricmc.api.ModInitializer;

public class VanillaEndervators implements ModInitializer{

    @Override
    public void onInitialize() {
        Config.loadConfig();
        Blocks.registerBlocks();
    }

}
package com.extracraftx.minecraft.vanillaendervators.blocks;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static EndervatorBlock[] endervators = new EndervatorBlock[16];

    public static String[] colorNames = new String[] { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime",
            "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black" };

    public static void registerBlocks() {
        for (int i = 0; i < colorNames.length; i++) {
            String name = colorNames[i];
            String blockName = name + " Endervator";
            String codeName = name.toLowerCase().replaceAll(" ", "_");
            Identifier ident = new Identifier("vanillaendervators", codeName + "_endervator");
            endervators[i] = Registry.register(Registry.BLOCK, ident,
                    new EndervatorBlock(Registry.BLOCK.get(new Identifier("minecraft", codeName + "_wool")), ident, blockName));
            Registry.register(Registry.ITEM, ident, endervators[i].getNewBlockItem());
        }
    }
}
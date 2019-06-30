package com.extracraftx.minecraft.vanillaendervators.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface ActionListenerBlock{
    boolean onJump(BlockPos pos, PlayerEntity player);
    boolean onSneak(BlockPos pos, PlayerEntity player);
}
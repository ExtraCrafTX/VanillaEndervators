package com.extracraftx.minecraft.vanillaendervators.mixin;

import com.extracraftx.minecraft.vanillaendervators.interfaces.ActionListenerBlock;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity{

    public ServerPlayerEntityMixin(){
        super(null, null);
    }

    @Override
    public void jump() {
        BlockPos pos = this.getBlockPos().down();
        Block b = this.world.getBlockState(pos).getBlock();
        if(b instanceof ActionListenerBlock){
            ActionListenerBlock block = (ActionListenerBlock)b;
            if(block.onJump(pos, this))
                return;
        }
        super.jump();
    }

    @Override
    public void setSneaking(boolean sneak) {
        super.setSneaking(sneak);
        if(sneak){
            BlockPos pos = this.getBlockPos().down();
            Block b = this.world.getBlockState(pos).getBlock();
            if(b instanceof ActionListenerBlock){
                ActionListenerBlock block = (ActionListenerBlock)b;
                block.onSneak(pos, this);
            }
        }
    }

}
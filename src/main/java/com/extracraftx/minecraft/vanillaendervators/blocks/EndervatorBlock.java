package com.extracraftx.minecraft.vanillaendervators.blocks;

import com.extracraftx.minecraft.serveradditionsutil.block.ServerSideBlock;
import com.extracraftx.minecraft.serveradditionsutil.item.ServerSideBlockItem;
import com.extracraftx.minecraft.vanillaendervators.interfaces.ActionListenerBlock;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EndervatorBlock extends ServerSideBlock implements ActionListenerBlock {

    public EndervatorBlock(Block rep, Identifier id) {
        super(rep, id, FabricBlockSettings.copy(rep).build());
    }

    public ServerSideBlockItem getNewBlockItem() {
        return new ServerSideBlockItem(this, this.getId(), new Item.Settings().group(ItemGroup.TRANSPORTATION));
    }

    @Override
    public boolean onJump(BlockPos pos, PlayerEntity player) {
        if (player.isSprinting())
            return false;
        pos = pos.up();
        World world = player.world;
        while (pos.getY() < 256) {
            Block b = world.getBlockState(pos).getBlock();
            if (b instanceof EndervatorBlock) {
                EndervatorBlock block = (EndervatorBlock) b;
                if (block.materialColor == this.materialColor) {
                    Vec3d position = player.getPos();
                    final BlockPos newPos = pos.up();
                    player.requestTeleport(position.x, newPos.getY(), position.z);
                    player.damage(DamageSource.FALL, 3f);
                    world.playSound(null, position.x, newPos.getY() + player.getStandingEyeHeight(), position.z,
                            SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5f, 1.122462f);
                    return true;
                }
            }
            pos = pos.up();
        }
        return false;
    }

    @Override
    public boolean onSneak(BlockPos pos, PlayerEntity player) {
        pos = pos.down();
        World world = player.world;
        while(pos.getY() >= 0){
            Block b = world.getBlockState(pos).getBlock();
            if(b instanceof EndervatorBlock){
                EndervatorBlock block = (EndervatorBlock)b;
                if(block.materialColor == this.materialColor){
                    Vec3d position = player.getPos();
                    final BlockPos newPos = pos.up();
                    player.requestTeleport(position.x, newPos.getY(), position.z);
                    player.damage(DamageSource.FALL, 3f);
                    world.playSound(null, position.x, newPos.getY() + player.getStandingEyeHeight(), position.z,
                            SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5f, 0.890899f);
                    return true;
                }
            }
            pos = pos.down();
        }
        return false;
    }

}
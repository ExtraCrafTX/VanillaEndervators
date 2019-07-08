package com.extracraftx.minecraft.vanillaendervators.blocks;

import java.util.Random;

import com.extracraftx.minecraft.serveradditionsutil.block.ServerSideBlock;
import com.extracraftx.minecraft.serveradditionsutil.item.ServerSideBlockItem;
import com.extracraftx.minecraft.vanillaendervators.config.Config;
import com.extracraftx.minecraft.vanillaendervators.interfaces.ActionListenerBlock;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EndervatorBlock extends ServerSideBlock implements ActionListenerBlock {

    private String name;

    public EndervatorBlock(Block rep, Identifier id, String name) {
        super(rep, id, FabricBlockSettings.copy(rep).build());
        this.name = name;
    }

    public ServerSideBlockItem getNewBlockItem() {
        return new EndervatorBlockItem();
    }

    @Override
    public boolean onJump(BlockPos pos, PlayerEntity player) {
        if (!Config.INSTANCE.teleportWhileSprinting && player.isSprinting())
            return false;
        pos = pos.up();
        ServerWorld world = (ServerWorld) player.world;
        int count = 0;
        while (count < Config.INSTANCE.maxRange && pos.getY() < 256) {
            Block b = world.getBlockState(pos).getBlock();
            if (b instanceof EndervatorBlock) {
                EndervatorBlock block = (EndervatorBlock) b;
                if (!Config.INSTANCE.sameColourOnly || block.materialColor == this.materialColor) {
                    Vec3d position = player.getPos();
                    final BlockPos newPos = pos.up();
                    if(Config.INSTANCE.allowBlocked || !world.getBlockState(newPos).canSuffocate(world, newPos)){
                        player.requestTeleport(position.x, newPos.getY(), position.z);
                        if(Config.INSTANCE.teleportDamage > 0)
                            player.damage(DamageSource.FALL, Config.INSTANCE.teleportDamage);
                        world.playSound(null, position.x, newPos.getY() + player.getStandingEyeHeight(), position.z,
                                SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5f, 1.122462f);
                        world.spawnParticles(ParticleTypes.END_ROD, position.x, newPos.getY(), position.z, 10, 0, 0, 0, 0.25);
                        return true;
                    }
                }
            }
            pos = pos.up();
            count++;
        }
        return false;
    }

    @Override
    public boolean onSneak(BlockPos pos, PlayerEntity player) {
        if (!Config.INSTANCE.teleportWhileSprinting && player.isSprinting())
            return false;
        pos = pos.down();
        ServerWorld world = (ServerWorld) player.world;
        int count = 0;
        while(count < Config.INSTANCE.maxRange && pos.getY() >= 0){
            Block b = world.getBlockState(pos).getBlock();
            if(b instanceof EndervatorBlock){
                EndervatorBlock block = (EndervatorBlock)b;
                if(!Config.INSTANCE.sameColourOnly || block.materialColor == this.materialColor){
                    Vec3d position = player.getPos();
                    final BlockPos newPos = pos.up();
                    if(Config.INSTANCE.allowBlocked || !world.getBlockState(newPos).canSuffocate(world, newPos)){
                        player.requestTeleport(position.x, newPos.getY(), position.z);
                        if(Config.INSTANCE.teleportDamage > 0)
                            player.damage(DamageSource.FALL, Config.INSTANCE.teleportDamage);
                        world.playSound(null, position.x, newPos.getY() + player.getStandingEyeHeight(), position.z,
                                SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5f, 0.890899f);
                        world.spawnParticles(ParticleTypes.END_ROD, position.x, newPos.getY(), position.z, 10, 0, 0, 0, 0.25);
                        return true;
                    }
                }
            }
            pos = pos.down();
            count++;
        }
        return false;
    }

    @Override
    public void onBlockAdded(BlockState oldState, World world, BlockPos pos, BlockState newState,
            boolean b) {
        super.onBlockAdded(oldState, world, pos, newState, b);
        if(world.isClient)
            return;
        if(!world.getBlockTickScheduler().isTicking(pos, this)){
            world.getBlockTickScheduler().schedule(pos, this, 5);
        }
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockHitResult hitResult) {
        if(world.isClient)
            return false;
        if(!world.getBlockTickScheduler().isTicking(pos, this)){
            world.getBlockTickScheduler().schedule(pos, this, 5);
        }
        return false;
    }

    @Override
    public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random) {
        if(world.isClient)
            return;
        world.getBlockTickScheduler().schedule(pos, this, 15);
        ServerWorld sw = (ServerWorld) world;
        sw.spawnParticles(ParticleTypes.END_ROD, pos.getX()+0.5f, pos.getY()+1.25f, pos.getZ()+0.5f, 3, 0.25, 0.125, 0.25, 0.01);
    }

    class EndervatorBlockItem extends ServerSideBlockItem{
        public EndervatorBlockItem(){
            super(EndervatorBlock.this, EndervatorBlock.this.getId(), new Item.Settings().group(ItemGroup.TRANSPORTATION), EndervatorBlock.this.name);
        }

        @Override
        public ItemStack getClientItemStack(ItemStack original) {
            ItemStack def = super.getClientItemStack(original);
            def.addEnchantment(null, 0);
            return def;
        }
    }

}
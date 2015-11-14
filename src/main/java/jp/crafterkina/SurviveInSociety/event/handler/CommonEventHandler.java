/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.event.handler;

import jp.crafterkina.SurviveInSociety.block.entities.TileEntityBulletinBoard;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;

/**
 * general event handler
 */
public enum CommonEventHandler{
    instance;

    @SubscribeEvent
    public void onPlayerSleepInBed(PlayerSleepInBedEvent event){
        World world = event.entityPlayer.worldObj;
        if(world != null && !world.isRemote && world instanceof WorldServer && ((WorldServer) world).areAllPlayersAsleep()){
            @SuppressWarnings("unchecked") Iterator<? extends TileEntity> iterator = world.loadedTileEntityList.iterator();
            for(TileEntity te = null; iterator.hasNext(); te = iterator.next()){
                if(te != null && te instanceof TileEntityBulletinBoard){
                    ((TileEntityBulletinBoard) te).updateContent();
                }
            }
        }
    }
}

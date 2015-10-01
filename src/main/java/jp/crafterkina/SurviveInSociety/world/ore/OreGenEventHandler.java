/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 */

package jp.crafterkina.SurviveInSociety.world.ore;

import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public enum OreGenEventHandler{
    instance;

    @SubscribeEvent
    public void pre(OreGenEvent.Pre event){

    }

    @SubscribeEvent
    public void post(OreGenEvent.Post event){

    }
}
